package com.duydev.backend.presentation.dto.request;

import java.math.BigDecimal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateCarDto {
    @NotNull(message = "OWNER_ID_NOT_NULL")
    Long ownerId;

    @NotNull(message = "BRAND_NOT_NULL")
    String brand;

    @NotNull(message = "MODEL_NOT_NULL")
    String model;

    @NotNull(message = "LICENSE_PLATE_NOT_NULL")
    String licensePlate;

    @NotNull(message = "YEAR_NOT_NULL")
    Integer year;

    @NotNull(message = "PRICE_PER_HOUR_NOT_NULL")
    BigDecimal pricePerHour;

    @Valid
    @NotNull(message = "LOCATION_NOT_NULL")
    RequestLocationDto location;

}
