package com.duydev.backend.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegisterDto {
    private String username;
    private String password;
    private String email;
}
