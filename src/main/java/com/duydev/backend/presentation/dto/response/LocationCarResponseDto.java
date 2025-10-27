package com.duydev.backend.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationCarResponseDto {
    private String name;
    private String province;
    private String ward;
}
