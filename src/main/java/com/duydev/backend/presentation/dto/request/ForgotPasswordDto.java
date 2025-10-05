package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgotPasswordDto {
    @NotNull(message = "EMAIL_NOT_NULL")
    @NotBlank(message = "EMAIL_NOT_BLANK")
    private String email;
}
