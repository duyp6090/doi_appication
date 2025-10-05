package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestResetPasswordDto {
    @NotNull(message = "EMAIL_NOT_NULL")
    @NotBlank(message = "EMAIL_NOT_BLANK")
    private String email;

    @NotNull(message = "NEWPASSWORD_NOT_NULL")
    @NotBlank(message = "NEWPASSWORD_NOT_BLANK")
    private String newPassword;

    @NotNull(message = "OTP_NOT_NULL")
    @NotBlank(message = "OTP_NOT_BLANK")
    private String otp;
}
