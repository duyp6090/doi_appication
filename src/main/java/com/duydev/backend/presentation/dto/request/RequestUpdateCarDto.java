package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class RequestUpdateCarDto {
    @NotNull(message = "OWNER_ID_NOT_NULL")
    private Long ownerId;

    @NotNull(message = "CAR_ID_NOT_NULL")
    private Long carId;

    private String brand;

    private String model;

    private String licensePlate;

    private Integer year;

    private BigDecimal pricePerHour;

    private String nameLocation;

    private String province;

    private String ward;

    private Double latitude;

    private Double longitude;

    List<String> publishIds;

}
