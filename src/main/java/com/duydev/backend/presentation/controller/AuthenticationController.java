package com.duydev.backend.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duydev.backend.application.service.interfaceservice.IAuthenticationService;
import com.duydev.backend.presentation.dto.request.RequestLoginDto;
import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
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
    public String changePassword(@RequestBody String entity) {
        return entity;
    }

    @PostMapping("/forgot-password")
    public String forgotPassword() {
        return "Forgot password successfully";
    }

    @PostMapping("/reset-password")
    public String resetPassword() {
        return "Reset password successfully";
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
