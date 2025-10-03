package com.duydev.backend.presentation.dto.request;

import com.duydev.backend.domain.enums.TypeUser;
import com.duydev.backend.util.anotation.anotationpattern.EnumPattern;

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

    @EnumPattern(enumClass = TypeUser.class, message = "INVALID_TYPE_USER")
    private TypeUser typeUser = TypeUser.CUSTOMER;
}
