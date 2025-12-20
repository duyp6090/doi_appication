package com.duydev.backend.application.service;

import com.duydev.backend.domain.enums.StatusUser;
import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.domain.model.RoleEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.OtpRedisRepository;
import com.duydev.backend.domain.repositories.RoleRepository;
import com.duydev.backend.domain.repositories.TokenRedisRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.util.EmailUtil;
import com.duydev.backend.util.JwtUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthenticationServiceImpl Unit Test")
class AuthenticationServiceImplTest {

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
    private AuthenticationServiceImpl authenticationService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private User existingUser;

    @BeforeEach
    void setUp() {
        existingUser = User.builder()
                .username("testUser")
                .password("password123")
                .statusUser(StatusUser.ACTIVE)
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