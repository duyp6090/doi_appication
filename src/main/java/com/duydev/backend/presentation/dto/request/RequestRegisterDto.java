package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegisterDto {
    @NotNull(message = "Username not null")
    private String username;

    @NotNull(message = "Password not null")
    private String password;
}
