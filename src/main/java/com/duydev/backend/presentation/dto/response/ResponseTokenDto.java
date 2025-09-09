package com.duydev.backend.presentation.dto.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseTokenDto implements Serializable{
    private String accessToken;
    private String refreshToken;
    private Long userId;
    
}
