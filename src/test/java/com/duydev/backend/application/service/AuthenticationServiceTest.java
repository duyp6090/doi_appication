package com.duydev.backend.application.service;

import com.duydev.backend.domain.enums.StatusUser;
import com.duydev.backend.domain.enums.TypeKey;
import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import com.duydev.backend.domain.model.RoleEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.OtpRedisRepository;
import com.duydev.backend.domain.repositories.RoleRepository;
import com.duydev.backend.domain.repositories.TokenRedisRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.infrastructure.util.EmailUtil;
import com.duydev.backend.infrastructure.util.JwtUtil;
import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseTokenDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthenticationServiceImpl Unit Test")
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRedisRepository tokenRedisRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private OtpRedisRepository otpRedisRepository;

    @Mock
    private EmailUtil emailUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private User existingUser;

    @BeforeEach
    void setUp() {
        existingUser = User.builder()
                .username("testUser")
                .password("password123")
                .statusUser(StatusUser.ACTIVE).
                userHasRoles(new HashSet<>())
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("Register Tests")
    class RegisterTests {

        private RequestRegisterDto requestRegisterDto;

        private RoleEntity customerRole;

        @BeforeEach
        void setUp() {
            requestRegisterDto = RequestRegisterDto.builder()
                    .username("testUser")
                    .password("password123")
                    .typeUser(TypeRole.CUSTOMER)
                    .statusUser(StatusUser.ACTIVE)
                    .build();

            customerRole = RoleEntity.builder()
                    .name(TypeRole.CUSTOMER)
                    .build();
        }

        @Test
        @DisplayName("Should register user successfully")
        void register_ShouldReturnSuccessResponse() {
            // arrange === given
            given(userRepository.findByUsername(requestRegisterDto.getUsername())).willReturn(existingUser);

            given(userRepository.save(any(User.class))).willAnswer(
                    invocation -> {
                        User user = invocation.getArgument(0);
                        user.setId(1L);
                        return user;
                    }
            );

            given(roleRepository.findByName(requestRegisterDto.getTypeUser())).willReturn(Optional.of(customerRole));

            given(passwordEncoder.encode(requestRegisterDto.getPassword())).willReturn("password123");

            // act === when
            ResponseDto<String> result = authenticationService.register(requestRegisterDto);

            // assert === then
            Assertions.assertNotNull(result);
            assertThat(result.getStatus()).isEqualTo(200);
            assertThat(result.getMessage()).contains("Register successfully");

            then(userRepository).should().save(userCaptor.capture());
            User savedUser = userCaptor.getValue();
            assertThat(savedUser.getUsername()).isEqualTo(requestRegisterDto.getUsername());
            assertThat(savedUser.getPassword()).isNotEqualTo(requestRegisterDto.getPassword());
            assertThat(savedUser.getUserHasRoles()).extracting("role.name").containsExactly(customerRole.getName());
        }

        @Test
        @DisplayName("Should throw exception when username already exists")
        void register_ShouldThrowExceptionWhenUsernameExists() {
            // arrange === given
            given(userRepository.findByUsername(requestRegisterDto.getUsername())).willReturn(null);

            // act === when
            // assert === then
            assertThatThrownBy(
                    () -> authenticationService.register(requestRegisterDto)
            ).isInstanceOf(RuntimeException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.USERNAME_EXIST
                    );
        }
    }

    @Nested
    @DisplayName("Login Tests")
    class LoginTests {

        private String username;
        private String password;
        private Long userId;
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_CUSTOMER"),
                new SimpleGrantedAuthority("READ_PERMISSION")
        );


        @BeforeEach
        void setUp() {
            username = "testUser";
            password = "password123";
            userId = 1L;
        }

        @Test
        @DisplayName("Should login successfully with valid credentials")
        void login_ShouldReturnSuccessResponse() {
            // arrange === given
            Authentication authentication = mock(Authentication.class);
            existingUser.setId(userId);
            given(authentication.getPrincipal()).willReturn(existingUser);
            doReturn(authorities).when(authentication).getAuthorities();

            given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).willReturn(authentication);

            given(jwtUtil.generateToken(eq(TypeKey.ACESSTOKEN), anyList(), eq(username))).willReturn("jwt-access-token");
            given(jwtUtil.generateToken(eq(TypeKey.REFRESHTOKEN), anyList(), eq(username))).willReturn("jwt-refresh-token");

            given(tokenRedisRepository.findByUsername(any(String.class))).willReturn(null);


            // act === when
            ResponseDto<ResponseTokenDto> result = authenticationService.login(username, password);

            // assert === then
            Assertions.assertNotNull(result);
            assertThat(result.getStatus()).isEqualTo(200);
            assertThat(result.getMessage()).contains("Login successfully");
            assertThat(result.getData()).isInstanceOf(ResponseTokenDto.class);
        }

        @Test
        @DisplayName("Should throw exception with invalid credentials")
        void login_ShouldThrowExceptionWithInvalidCredentials() {
            // arrange === given
            given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).willThrow(
                    new AuthenticationException("Invalid credentials") {
                    }
            );
            // act === when
            // assert === then
            assertThatThrownBy(
                    () -> authenticationService.login(username, password)
            ).isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.INVALID_USERNAME_PASSWORD);
        }

    }

    @Nested
    @DisplayName("Logout Tests")
    class LogoutTests {
    }

    @Nested
    @DisplayName("Token Refresh Tests")
    class TokenRefreshTests {
    }

    @Nested
    @DisplayName("Password Management Tests")
    class PasswordManagementTests {
    }

    @Test
    void register() {
    }

    @Test
    void loginSocial() {
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void requestSocial() {
    }

    @Test
    void forgotPassword() {
    }

    @Test
    void resetPassword() {
    }
}