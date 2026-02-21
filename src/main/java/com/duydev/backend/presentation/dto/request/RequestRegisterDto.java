package com.duydev.backend.presentation.dto.request;

import com.duydev.backend.domain.enums.StatusUser;
import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.util.anotation.anotationpattern.EnumPattern;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestRegisterDto {
    @NotNull(message = "USERNAME_NOT_NULL")
    private String username;

    @NotNull(message = "PASSWORD_NOT_NULL")
    private String password;

    @EnumPattern(enumClass = TypeRole.class, message = "INVALID_TYPE_USER")
    private TypeRole typeUser = TypeRole.CUSTOMER;

    @EnumPattern(enumClass = StatusUser.class, message = "INVALID_STATUS_USER")
    private StatusUser statusUser = StatusUser.ACTIVE;
}
