package com.duydev.backend.application.service.interfaceservice;

import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseTokenDto;

import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationService {
    public ResponseDto<String> register(RequestRegisterDto requestRegisterDto);

    public ResponseDto<ResponseTokenDto> loginSocial(String code, String provider);

    public ResponseDto<ResponseTokenDto> login(String username, String password);

    public ResponseDto<String> logout(HttpServletRequest request);

    public ResponseDto<String> changePassword(String username, String oldPassword, String newPassword);

    public void forgotPassword(String email);

    public ResponseDto<String> resetPassword(String email, String otp, String newPassword);

    public ResponseDto<ResponseTokenDto> refreshToken(HttpServletRequest request);

    public ResponseDto<String> requestSocial(HttpServletRequest request, String provider);

}
