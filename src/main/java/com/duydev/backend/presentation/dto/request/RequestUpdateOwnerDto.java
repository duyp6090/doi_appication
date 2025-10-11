package com.duydev.backend.presentation.dto.request;

import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.util.anotation.anotationpattern.EnumPattern;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateOwnerDto {
    @NotNull(message = "USER_ID_NOT_NULL")
    Long userId;

    @EnumPattern(enumClass = TypeRole.class, message = "INVALID_TYPE_USER")
    TypeRole typeRole = TypeRole.OWNER;
}
