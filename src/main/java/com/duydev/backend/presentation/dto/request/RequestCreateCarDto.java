package com.duydev.backend.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RequestCreateCarDto {
    @NotNull(message = "OWNER_ID_NOT_NULL")
    private Long ownerId;

    @NotNull(message = "BRAND_NOT_NULL")
    private String brand;

    @NotNull(message = "MODEL_NOT_NULL")
    private String model;

    @NotNull(message = "LICENSE_PLATE_NOT_NULL")
    private String licensePlate;

    @NotNull(message = "YEAR_NOT_NULL")
    private Integer year;

    @NotNull(message = "PRICE_PER_HOUR_NOT_NULL")
    private BigDecimal pricePerHour;

    @Valid
    @NotNull(message = "LOCATION_NOT_NULL")
    private RequestLocationDto location;

}
