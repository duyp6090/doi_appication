package com.duydev.backend.application.service;

import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.domain.model.RoleEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.model.UserHasRoleEntity;
import com.duydev.backend.domain.repositories.RoleRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestUpdateInformationAccount;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseUserInformationDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("Account Service Test")
class AccountServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AccountService accountService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private User userMock;
    private final Long USER_ID = 1L;

    @BeforeEach
    void setUp() {
        userMock = User.builder()
                .username("testUser")
                .email("abc@gm.uit.edu.vn")
                .phone("123456789")
                .birthDate(LocalDate.parse("2000-01-01"))
                .build();
        userMock.setId(1L);
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("updateUserToOwner() Tests")
    class UpdateUserToOwnerTests {

        private RoleEntity ownerRole;

        @BeforeEach
        void setUp() {
            // Setup role USER
            RoleEntity userRole = RoleEntity.builder()
                    .name(TypeRole.CUSTOMER)
                    .build();

            // Setup role OWNER
            ownerRole = RoleEntity.builder()
                    .name(TypeRole.OWNER)
                    .build();

            // Setup user with USER role
            UserHasRoleEntity existingUserRole = UserHasRoleEntity.builder()
                    .role(userRole)
                    .build();

            Set<UserHasRoleEntity> userHasRoles = new HashSet<>();
            userHasRoles.add(existingUserRole);

            userMock.setUserHasRoles(userHasRoles);

            // Set back reference to user in UserHasRoleEntity
            existingUserRole.setUser(userMock);
        }

        @Test
        @DisplayName("Should update user to owner successfully")
        void updateUserToOwner_ShouldReturnSuccessResponse() {
            // Arrange === Given
            given(userRepository.findById(USER_ID)).willReturn(Optional.of(userMock));

            given(roleRepository.findByName(TypeRole.OWNER)).willReturn(Optional.of(ownerRole));

            given(userRepository.save(any(User.class))).willAnswer(invocation -> invocation.getArgument(0));

            // Act === When
            ResponseDto<String> result = accountService.updateUserToOwner(USER_ID, TypeRole.OWNER);

            // Assert === Then
            assertNotNull(result);
            assertThat(result.getMessage()).contains("Update user to owner successfully");
            assertThat(result.getStatus()).isEqualTo(200);

            then(userRepository).should(times(1)).findById(USER_ID);
            then(roleRepository).should(times(1)).findByName(TypeRole.OWNER);
            then(userRepository).should(times(1)).save(userCaptor.capture());
            User savedUser = userCaptor.getValue();
            assertThat(savedUser.getUserHasRoles()).extracting("role.name").contains(TypeRole.OWNER);

        }

        @Test
        @DisplayName("Should throw USER_NOT_FOUND exception when user does not exist")
        void updateUserToOwner_ShouldThrowUserNotFoundException() {

            // Arrange === Given
            given(userRepository.findById(USER_ID)).willReturn(Optional.empty());

            // Act === When
            // Assert === Then
            assertThatThrownBy(
                    () -> accountService.updateUserToOwner(USER_ID, TypeRole.OWNER)
            ).isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.USER_NOT_FOUND);
        }

    }

    @Nested
    @DisplayName("getUserInformation() Tests")
    class GetUserInformationTests {

        @Test
        @DisplayName("Should return user information successfully")
        void getUserInformation_ShouldReturnUserInformation() {
            // arrange === Given
            // act === When
            try (MockedStatic<SecurityContextHolder> mockedSecurity = mockStatic(SecurityContextHolder.class)) {
                SecurityContext securityContextMock = Mockito.mock(SecurityContext.class);
                Authentication authenticationMock = Mockito.mock(Authentication.class);

                mockedSecurity.when(SecurityContextHolder::getContext).thenReturn(securityContextMock);

                given(securityContextMock.getAuthentication()).willReturn(authenticationMock);
                given(authenticationMock.getPrincipal()).willReturn(userMock);

                ResponseDto<ResponseUserInformationDto> result = accountService.getUserInformation();

                // assert === Then
                assertNotNull(result);
                assertThat(result.getMessage()).contains("Get user information successfully");
                assertThat(result.getStatus()).isEqualTo(200);
                assertThat(result.getData()).extracting("username").isEqualTo(userMock.getUsername());
                assertThat(result.getData()).extracting("email").isEqualTo(userMock.getEmail());
                assertThat(result.getData()).extracting("phone").isEqualTo(userMock.getPhone());
                assertThat(result.getData()).extracting("birthDate").isEqualTo(userMock.getBirthDate());
            }
        }
    }

    @Nested
    @DisplayName("updateUserInformation() Tests")
    class UpdateUserInformationTests {

        private RequestUpdateInformationAccount request;

        @BeforeEach
        void setUp() {
            request = RequestUpdateInformationAccount.builder()
                    .email("abc@gmail.com")
                    .phone("12234354")
                    .birthDate(LocalDate.parse("2000-01-01"))
                    .build();
        }

        @Test
        @DisplayName("Should update user information successfully")
        void updateUserInformation_ShouldReturnSuccessResponse() {
            // arrange === Given
            // act === When
            try (MockedStatic<SecurityContextHolder> mockedSecurity = mockStatic(SecurityContextHolder.class)) {
                SecurityContext securityContextMock = Mockito.mock(SecurityContext.class);
                Authentication authenticationMock = Mockito.mock(Authentication.class);

                mockedSecurity.when(SecurityContextHolder::getContext).thenReturn(securityContextMock);
                given(securityContextMock.getAuthentication()).willReturn(authenticationMock);
                given(authenticationMock.getPrincipal()).willReturn(userMock);

                ResponseDto<String> result = accountService.updateUserInformation(request);

                // assert === Then
                then(userRepository).should(times(1)).save(userCaptor.capture());
                User savedUser = userCaptor.getValue();
                assertThat(savedUser.getEmail()).isEqualTo(request.getEmail());
                assertThat(savedUser.getPhone()).isEqualTo(request.getPhone());
                assertThat(savedUser.getBirthDate()).isEqualTo(request.getBirthDate());
            }

        }
    }
}