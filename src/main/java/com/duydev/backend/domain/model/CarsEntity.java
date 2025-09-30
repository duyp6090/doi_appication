package com.duydev.backend.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_cars")
public class CarsEntity extends AbstractEntity<Long>{

    @Column(name = "owner_id")
    Long ownerId;

    @Column(name = "brand")
    String brand;

    @Column(name = "model")
    String model;

    @Column(name = "license_plate")
    String licensePlate;

    @Column(name = "year")
    Integer year;

    @Column(name = "price_per_hour")
    BigDecimal pricePerHour;

    @Column(name = "location_id")
    Long locationId;

    @Column(name = "images", columnDefinition = "jsonb")
    private String images;
}
