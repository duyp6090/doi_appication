package com.duydev.backend.domain.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duydev.backend.application.mapper.CarsResponseProjection;
import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.presentation.dto.request.RequestGetListCarsDto;

@Repository
public interface CarsRepository extends JpaRepository<CarsEntity, Long> {
    @Query(value = """
            SELECT
                c.id AS id,
                c.brand AS brand,
                c.model AS model,
                c.year AS year,
                c.images as images,
                c.price_per_hour AS pricePerHour,
                loc.name AS locationName,
                loc.province as province,
                loc.ward as ward,
                loc.longitude as longitude,
                loc.latitude as latitude,
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
            GROUP BY c.id, c.brand, c.model, c.year, c.price_per_hour, loc.name, loc.province, loc.ward, loc.longitude, loc.latitude
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
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            Pageable pageable);

    @Query(value = """
                    SELECT CASE WHEN COUNT(b) = 0 THEN true ELSE false END
                    FROM BookingEntity b
                    WHERE b.car.id = :carId
                    AND b.startTime <= :endTime
                    AND b.endTime >= :startTime
                    AND (b.status  = 'CONFIRMED' OR (b.status = 'PENDING' AND b.customer.id = :customerId))
            """)
    public boolean validBookingTime(
            @Param("carId") Long carId,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("customerId") Long customerId);

    @Query(value = """
                    SELECT c
                    FROM CarsEntity c
                    WHERE c.user.id = :#{#request.getOwnerId()}
                    AND (:#{#request.getBrand()} IS NULL OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :#{#request.getBrand()}, '%')))
                    AND (:#{#request.getYear()} IS NULL OR c.year = :#{#request.getYear()})
                    AND (:#{#request.getProvince()} IS NULL OR LOWER(c.location.province) LIKE LOWER(CONCAT('%', :#{#request.getProvince()}, '%')))
                    AND (:#{#request.getWard()} IS NULL OR LOWER(c.location.ward) LIKE LOWER(CONCAT('%', :#{#request.getWard()}, '%')))
                    AND (:#{#request.getMinPrice()} IS NULL OR c.pricePerHour >= :#{#request.getMinPrice()})
                    AND (:#{#request.getMaxPrice()} IS NULL OR c.pricePerHour <= :#{#request.getMaxPrice()})
            """)
    public Page<CarsEntity> findListCars(
            @Param("request") RequestGetListCarsDto request,
            Pageable pageable);

    @Query(value = """
                            SELECT c
                            FROM CarsEntity c
                            LEFT JOIN FETCH c.bookings b
                            WHERE c.id = :carId
            """)
    public CarsEntity findOneCar(@Param("carId") Long carId);

    @Query(value = """
                SELECT c
                FROM CarsEntity c
                LEFT JOIN FETCH c.bookings b
                WHERE c.user.id = :userId
                    AND b.startTime <= :endTime
                    AND b.endTime >= :startTime

            """)
    public List<CarsEntity> findCarsAndGetBookingsByUserId(
            @Param("userId") Long userId,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

}
