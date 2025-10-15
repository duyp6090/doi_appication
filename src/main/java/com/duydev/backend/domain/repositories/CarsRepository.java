package com.duydev.backend.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.presentation.dto.request.RequestGetCarsDto;
import com.duydev.backend.presentation.dto.response.CarsResponseDto;

@Repository
public interface CarsRepository extends JpaRepository<CarsEntity, Long> {
    @Query("""
                SELECT NEW com.duydev.backend.presentation.dto.response.CarsResponseDto(
                    c.id,
                    c.brand,
                    c.model,
                    c.year,
                    c.pricePerHour,
                    CASE
                        WHEN loc.longitude IS NOT NULL AND loc.latitude IS NOT NULL
                            AND :#{#requestGetCarsDto.getLongitude()} IS NOT NULL
                            AND :#{#requestGetCarsDto.getLatitude()} IS NOT NULL
                        THEN CAST(
                                FUNCTION('ST_DistanceSphere',
                                    FUNCTION('ST_MakePoint', loc.longitude, loc.latitude),
                                    FUNCTION('ST_MakePoint', :#{#requestGetCarsDto.getLongitude()}, :#{#requestGetCarsDto.getLatitude()}))
                                AS double
                            )
                        ELSE 0.0
                    END
                )
                FROM CarsEntity c
                LEFT JOIN c.location loc
                LEFT JOIN c.bookings b
                WHERE (:#{#requestGetCarsDto.getBrand()} IS NULL OR c.brand = :#{#requestGetCarsDto.getBrand()})
                  AND (:#{#requestGetCarsDto.getYear()} IS NULL OR c.year = :#{#requestGetCarsDto.getYear()})
                  AND (:#{#requestGetCarsDto.getProvince()} IS NULL OR loc.province = :#{#requestGetCarsDto.getProvince()})
                  AND (:#{#requestGetCarsDto.getWard()} IS NULL OR loc.ward = :#{#requestGetCarsDto.getWard()})
                  AND (:#{#requestGetCarsDto.getMinPrice()} IS NULL OR c.pricePerHour >= :#{#requestGetCarsDto.getMinPrice()})
                  AND (:#{#requestGetCarsDto.getMaxPrice()} IS NULL OR c.pricePerHour <= :#{#requestGetCarsDto.getMaxPrice()})
                  AND (b.endTime >= :#{#requestGetCarsDto.getStartTime()} OR b.id IS NULL)
                GROUP BY c.id, c.brand, c.model, c.year, c.pricePerHour, loc.longitude, loc.latitude
                HAVING SUM(
                    CASE
                        WHEN b.id IS NOT NULL
                             AND b.status = 'CONFIRMED'
                             AND b.endTime > :#{#requestGetCarsDto.getStartTime()}
                             AND b.startTime < :#{#requestGetCarsDto.getEndTime()}
                        THEN 1
                        ELSE 0
                    END
                ) = 0
            """)
    public Page<CarsResponseDto> findCars(@Param("requestGetCarsDto") RequestGetCarsDto requestGetCarsDto,
            Pageable pageable);
}
