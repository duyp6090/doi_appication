package com.duydev.backend.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duydev.backend.application.service.interfaceservice.IAuthenticationService;
import com.duydev.backend.presentation.dto.request.ForgotPasswordDto;
import com.duydev.backend.presentation.dto.request.RequestChangePasswordDto;
import com.duydev.backend.presentation.dto.request.RequestLoginDto;
import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
import com.duydev.backend.presentation.dto.request.RequestResetPasswordDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseTokenDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j(topic = "AuthenticationController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<ResponseTokenDto>> login(
            @Valid @RequestBody RequestLoginDto requestLoginDto) {
        ResponseDto<ResponseTokenDto> response = authenticationService.login(
                requestLoginDto.getUsername(), requestLoginDto.getPassword());

        return ResponseEntity.ok(response);

    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<String>> register(
            @Valid @RequestBody RequestRegisterDto requestRegisterDto) {
        ResponseDto<String> response = authenticationService.register(requestRegisterDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseDto<String>> changePassword(
            @Valid @RequestBody RequestChangePasswordDto requestChangePassword) {
        String username = requestChangePassword.getUsername();
        String oldPassword = requestChangePassword.getOldPassword();
        String newPassword = requestChangePassword.getNewPassword();
        ResponseDto<String> response = authenticationService.changePassword(username, oldPassword, newPassword);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {
        String email = forgotPasswordDto.getEmail();
        authenticationService.forgotPassword(email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto<String>> resetPassword(
            @RequestBody RequestResetPasswordDto requestResetPasswordDto) {
        String email = requestResetPasswordDto.getEmail();
        String newPassword = requestResetPasswordDto.getNewPassword();
        String otp = requestResetPasswordDto.getOtp();

        ResponseDto<String> response = authenticationService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseDto<ResponseTokenDto>> refreshToken(HttpServletRequest request) {
        ResponseDto<ResponseTokenDto> response = authenticationService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDto<String>> logout(HttpServletRequest request) {
        ResponseDto<String> response = authenticationService.logout(request);
        return ResponseEntity.ok(response);
    }

}
