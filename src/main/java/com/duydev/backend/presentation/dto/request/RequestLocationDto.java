package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestLocationDto {
    @NotNull(message = "NAME_ADDRESS_NOT_NULL")
    @NotBlank(message = "NAME_ADDRESS_NOT_BLANK")
    private String name;

    @NotNull(message = "PROVINCE_NOT_NULL")
    @NotBlank(message = "PROVINCE_NOT_BLANK")
    private String province;

    @NotNull(message = "WARD_NOT_NULL")
    @NotBlank(message = "WARD_NOT_BLANK")
    private String ward;

    private Double latitude;

    private Double longitude;
}
