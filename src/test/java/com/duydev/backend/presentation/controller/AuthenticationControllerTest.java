package com.duydev.backend.presentation.controller;

import com.duydev.backend.application.service.interfaceservice.IAuthenticationService;
import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import com.duydev.backend.infrastructure.config.AppConfig;
import com.duydev.backend.infrastructure.config.security.CustomeUserDetailsService;
import com.duydev.backend.infrastructure.util.JwtUtil;
import com.duydev.backend.presentation.dto.request.RequestChangePasswordDto;
import com.duydev.backend.presentation.dto.request.RequestLoginDto;
import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseTokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@Import(AppConfig.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IAuthenticationService authenticationService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private CustomeUserDetailsService customeUserDetailsService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    private static Stream<Arguments> loginDataProvider() {
        // 1.Success case for user login full info
        ResponseTokenDto tokenSuccess = ResponseTokenDto.builder()
                .accessToken("access_token_duy")
                .refreshToken("refresh_token_duy")
                .userId(1001L)
                .build();

        ResponseDto<ResponseTokenDto> successLogin = ResponseDto.<ResponseTokenDto>builder()
                .status(200)
                .message(List.of("Login Success"))
                .data(tokenSuccess)
                .build();

        // 2. Others response cases when failure login
        // 2.1 Bad Request - Missing username or password
        ResponseDto<Object> badRequestResponse = ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(List.of("Bad request"))
                .data(null)
                .build();

        // 2.2 Unauthorized - Invalid username or password
        ResponseDto<Object> unauthorizedResponse = ResponseDto.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(List.of("Invalid username or password"))
                .data(null)
                .build();

        // 3. Return data
        return Stream.of(
                // Arguments.of(username, password, expectedResponse, exception)
                Arguments.of("duy_dev", "password123", successLogin, null),
                Arguments.of(null, "password123", badRequestResponse, null),
                Arguments.of("", "mypassword", unauthorizedResponse, new AppException(EnumException.INVALID_USERNAME_PASSWORD))
        );
    }

    @ParameterizedTest
    @MethodSource("loginDataProvider")
    void login(String userName, String password, ResponseDto<ResponseTokenDto> expectedResponse, RuntimeException ex) throws Exception {
        // 1. Prepare data
        RequestLoginDto request = new RequestLoginDto();
        request.setUsername(userName);
        request.setPassword(password);

        // 2. WHEN & THEN:
        if (ex != null) {
            when(authenticationService.login(userName, password)).thenThrow(ex);
        } else when(authenticationService.login(userName, password)).thenReturn(expectedResponse);

        // 3. Call API
        var action = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is(expectedResponse.getStatus()))
                .andExpect(jsonPath("$.message", containsInAnyOrder(expectedResponse.getMessage().toArray())));

        if (expectedResponse.getStatus() == HttpStatus.OK.value()) {
            action
                    .andExpect(jsonPath("$.data.accessToken").value(expectedResponse.getData().getAccessToken()))
                    .andExpect(jsonPath("$.data.userId").value(expectedResponse.getData().getUserId()));
        } else {
            action.andExpect(jsonPath("$.error").exists());
        }
    }

    static Stream<Arguments> registerDataProvider() {
        // 1. Create data for testing
        // 1.1 Successful registration
        RequestRegisterDto requestRegisterDtoValid = new RequestRegisterDto();
        requestRegisterDtoValid.setUsername("new_user");
        requestRegisterDtoValid.setPassword("StrongPassword123!");

        ResponseDto<String> successResponse = ResponseDto.<String>builder()
                .status(HttpStatus.OK.value())
                .message(List.of("Register successfully"))
                .build();

        // 1.2 Registration failure cases
        RequestRegisterDto requestRegisterDtoInvalid = new RequestRegisterDto();

        ResponseDto<String> failureResponse = ResponseDto.<String>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(List.of(EnumException.USERNAME_NOT_NULL.getMessage(), EnumException.PASSWORD_NOT_NULL.getMessage()))
                .build();

        // 2. Return mock data
        return Stream.of(
                Arguments.of(requestRegisterDtoValid, successResponse),
                Arguments.of(requestRegisterDtoInvalid, failureResponse)
        );
    }

    @ParameterizedTest
    @MethodSource("registerDataProvider")
    void register(RequestRegisterDto requestRegisterDto, ResponseDto<String> expectedResponse) throws Exception {
        // 1. WHEN & THEN
        when(authenticationService.register(requestRegisterDto)).thenReturn(expectedResponse);

        // 2. Call API
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestRegisterDto)))
                .andDo(print())
                .andExpect(status().is(expectedResponse.getStatus()))
                .andExpect(jsonPath("$.message", containsInAnyOrder(expectedResponse.getMessage().toArray())));
    }

    static Stream<Arguments> forgotPasswordDataProvider() {
        // 1. Prepare
        // 1.1 Successful change password
        RequestChangePasswordDto requestDtoValid = new RequestChangePasswordDto();
        requestDtoValid.setUsername("existing_user");
        requestDtoValid.setOldPassword("OldPassword123!");
        requestDtoValid.setNewPassword("NewStrongPassword456!");

        ResponseDto<String> responseDtoValid = ResponseDto.<String>builder()
                .status(HttpStatus.OK.value())
                .message(List.of("Change password successfully"))
                .build();

        // 1.2 Failure change password
        RequestChangePasswordDto requestDtoInvalid = new RequestChangePasswordDto();
        requestDtoInvalid.setUsername("existing_user");
        requestDtoInvalid.setOldPassword("WrongOldPassword!");

        ResponseDto<String> responseDtoInvalid = ResponseDto.<String>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(List.of(EnumException.NEWPASSWORD_NOT_BLANK.getMessage()))
                .build();

        // 2. Return data
        return Stream.of(
                Arguments.of(requestDtoInvalid, responseDtoInvalid),
                Arguments.of(requestDtoValid, responseDtoValid)
        );
    }

    @ParameterizedTest
    @MethodSource("forgotPasswordDataProvider")
    void changePassword(RequestChangePasswordDto requestChangePasswordDto, ResponseDto<String> expectedResponse) throws Exception {
        // 1. WHEN & THEN
        when(authenticationService.changePassword(
                requestChangePasswordDto.getUsername(),
                requestChangePasswordDto.getOldPassword(),
                requestChangePasswordDto.getNewPassword()
        )).thenReturn(expectedResponse);

        // 2. Call API
        mockMvc.perform(post("/api/v1/auth/change-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestChangePasswordDto)))
                .andDo(print())
                .andExpect(status().is(expectedResponse.getStatus()))
                .andExpect(jsonPath("$.message", containsInAnyOrder(expectedResponse.getMessage().toArray())));
    }

    @Test
    void forgotPassword() {
    }

    @Test
    void resetPassword() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void logout() throws Exception {
        // 1. Prepare data
        ResponseDto<String> responseDto = ResponseDto.<String>builder()
                .status(HttpStatus.OK.value())
                .message(List.of("Logout successfully"))
                .build();

        // 2. Mocking
        when(authenticationService.logout(any(HttpServletRequest.class))).thenReturn(responseDto);

        // 3. Call API
        mockMvc.perform(post("/api/v1/auth/logout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", containsInAnyOrder(responseDto.getMessage().toArray())));

        // 4. Verify
        verify(authenticationService, times(1)).logout(any(HttpServletRequest.class));
    }
}