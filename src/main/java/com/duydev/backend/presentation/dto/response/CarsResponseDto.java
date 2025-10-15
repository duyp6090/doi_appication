package com.duydev.backend.presentation.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarsResponseDto {
    Long id;
    String brand;
    String model;
    Integer year;
    BigDecimal pricePerHour;
    Double distance;
}
