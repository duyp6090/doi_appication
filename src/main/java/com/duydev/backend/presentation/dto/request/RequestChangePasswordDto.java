package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChangePasswordDto {
    @NotNull(message = "USERNAME_NOT_NULL")
    @NotBlank(message = "USERNAME_NOT_BLANK")
    private String username;

    @NotNull(message = "OLDPASSWORD_NOT_NULL")
    @NotBlank(message = "OLDPASSWORD_NOT_BLANK")
    private String oldPassword;

    @NotNull(message = "NEWPASSWORD_NOT_NULL")
    @NotBlank(message = "NEWPASSWORD_NOT_BLANK")
    private String newPassword;
}
