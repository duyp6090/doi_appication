package com.duydev.backend.application.mapper;

import java.math.BigDecimal;

public interface CarsResponseProjection {
    Long getId();

    String getBrand();

    String getModel();

    Integer getYear();

    BigDecimal getPricePerHour();

    String getImages();

    Double getDistance();
}
