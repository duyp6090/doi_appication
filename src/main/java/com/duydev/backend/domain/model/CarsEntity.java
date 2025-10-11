package com.duydev.backend.domain.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.ColumnTransformer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class CarsEntity extends AbstractEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User user;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "year")
    private Integer year;

    @Column(name = "price_per_hour")
    private BigDecimal pricePerHour;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;

    @Column(name = "images", columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String images;

    @OneToMany(mappedBy = "car")
    private List<BookingEntity> bookings;
}
