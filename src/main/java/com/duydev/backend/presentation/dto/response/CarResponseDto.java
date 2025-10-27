package com.duydev.backend.presentation.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private BigDecimal pricePerHour;
    private String images;
}
