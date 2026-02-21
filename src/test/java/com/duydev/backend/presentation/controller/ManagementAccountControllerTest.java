package com.duydev.backend.presentation.controller;

import com.duydev.backend.application.service.CustomeUserDetailsService;
import com.duydev.backend.application.service.interfaceservice.IAccountService;
import com.duydev.backend.config.AppConfig;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestUpdateOwnerDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManagementionAccountController.class)
@Import(AppConfig.class)
@WithMockUser(username = "duy_dev", roles = {"CUSTOMER"})
class ManagementAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
   
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private CustomeUserDetailsService customeUserDetailsService;

    @MockitoBean
    private IAccountService accountService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    static Stream<Arguments> updateToOwnerProvider() {
        // 1. Prepare data
        // 1.1 Success case
        RequestUpdateOwnerDto validRequest = new RequestUpdateOwnerDto();
        validRequest.setUserId(1L);

        ResponseDto<String> successResponse = ResponseDto.<String>builder()
                .status(HttpStatus.OK.value())
                .message(List.of("Update user to owner successfully"))
                .build();

        // 1.2 Failure case: invalid user ID
        RequestUpdateOwnerDto invalidUserIdRequest = new RequestUpdateOwnerDto();

        ResponseDto<String> invalidUserIdResponse = ResponseDto.<String>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(List.of(EnumException.USER_ID_NOT_NULL.getMessage()))
                .build();

        // 2. Return mock data
        return Stream.of(
                Arguments.of(validRequest, successResponse),
                Arguments.of(invalidUserIdRequest, invalidUserIdResponse)
        );
    }

    @ParameterizedTest
    @MethodSource("updateToOwnerProvider")
    void updateToOwner(RequestUpdateOwnerDto request, ResponseDto<String> expectedResponse) throws Exception {
        // 1. Mock service layer
        when(accountService.updateUserToOwner(request.getUserId(), request.getTypeRole()))
                .thenReturn(expectedResponse);

        // 2. Perform request and verify response
        mockMvc.perform(
                        patch("/api/v1/managemention-account/update-role")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().is(expectedResponse.getStatus()))
                .andExpect(jsonPath("$.message", containsInAnyOrder(expectedResponse.getMessage().toArray())));
    }

    @Test
    void updateUserInformation() {
    }

    @Test
    void getUserInformation() {
    }
}