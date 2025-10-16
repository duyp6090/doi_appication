package com.duydev.backend.domain.repositories;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duydev.backend.application.mapper.CarsResponseProjection;
import com.duydev.backend.domain.model.CarsEntity;

@Repository
public interface CarsRepository extends JpaRepository<CarsEntity, Long> {
    @Query(value = """
            SELECT
                c.id AS id,
                c.brand AS brand,
                c.model AS model,
                c.year AS year,
                c.price_per_hour AS pricePerHour,
                CASE
                    WHEN loc.longitude IS NOT NULL AND loc.latitude IS NOT NULL
                        AND :longitude IS NOT NULL
                        AND :latitude IS NOT NULL
                    THEN ST_DistanceSphere(
                        ST_MakePoint(loc.longitude::double precision, loc.latitude::double precision),
                        ST_MakePoint((:longitude)::double precision, (:latitude)::double precision)
                    )
                    ELSE 0.0
                END AS distance
            FROM tbl_cars c
            LEFT JOIN tbl_locations loc ON c.location_id = loc.id
            LEFT JOIN tbl_bookings b ON b.car_id = c.id
            WHERE (:brand IS NULL OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :brand, '%')))
            AND (:year IS NULL OR c.year = :year)
            AND (:province IS NULL OR LOWER(loc.province) LIKE LOWER(CONCAT('%', :province, '%')))
            AND (:ward IS NULL OR LOWER(loc.ward) LIKE LOWER(CONCAT('%', :ward, '%')))
            AND (:minPrice IS NULL OR c.price_per_hour >= :minPrice)
            AND (:maxPrice IS NULL OR c.price_per_hour <= :maxPrice)
            AND (b.id IS NULL OR b.end_time >= :startTime)
            GROUP BY c.id, c.brand, c.model, c.year, c.price_per_hour, loc.longitude, loc.latitude
            HAVING SUM(
                CASE
                    WHEN (
                        b.id IS NOT NULL
                        AND b.start_time <= :endTime
                        AND b.end_time >= :startTime
                    )
                    THEN 1
                    ELSE 0
                END
            ) = 0
            """, countQuery = """
                SELECT COUNT(*) FROM (
                    SELECT c.id
                    FROM tbl_cars c
                    LEFT JOIN tbl_locations loc ON c.location_id = loc.id
                    LEFT JOIN tbl_bookings b ON b.car_id = c.id
                    WHERE (:brand IS NULL OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :brand, '%')))
                    AND (:year IS NULL OR c.year = :year)
                    AND (:province IS NULL OR LOWER(loc.province) LIKE LOWER(CONCAT('%', :province, '%')))
                    AND (:ward IS NULL OR LOWER(loc.ward) LIKE LOWER(CONCAT('%', :ward, '%')))
                    AND (:minPrice IS NULL OR c.price_per_hour >= :minPrice)
                    AND (:maxPrice IS NULL OR c.price_per_hour <= :maxPrice)
                    AND (b.id IS NULL OR b.end_time >= :startTime)
                    GROUP BY c.id, c.brand, c.model, c.year, c.price_per_hour, loc.longitude, loc.latitude
                    HAVING SUM(
                        CASE
                            WHEN (
                                b.id IS NOT NULL
                                AND b.start_time <= :endTime
                                AND b.end_time >= :startTime
                            )
                            THEN 1
                            ELSE 0
                        END
                    ) = 0
                ) AS filtered_cars
            """, nativeQuery = true)
    public Page<CarsResponseProjection> findCars(
            @Param("brand") String brand,
            @Param("year") Integer year,
            @Param("province") String province,
            @Param("ward") String ward,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("longitude") Double longitude,
            @Param("latitude") Double latitude,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            Pageable pageable);
}
