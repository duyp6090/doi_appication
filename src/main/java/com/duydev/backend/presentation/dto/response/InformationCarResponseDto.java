package com.duydev.backend.presentation.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InformationCarResponseDto {
    String username;
    String phoneNumber;
    private String brand;
    private String model;
    private String licensePlate;
    private Integer year;
    private BigDecimal pricePerHour;
    private LocationCarResponseDto location;
    private String images;
    private List<ReviewBookingResponseDto> reviews;
}
