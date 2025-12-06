package com.duydev.backend.domain.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duydev.backend.domain.enums.StatusBooking;
import com.duydev.backend.domain.model.BookingEntity;

import jakarta.persistence.LockModeType;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
        boolean existsByCarIdAndEndTimeAfterAndStatus(Long carId, Date time, StatusBooking status);

        @Lock(LockModeType.PESSIMISTIC_WRITE)
        @Query("SELECT b FROM BookingEntity b WHERE b.id = :id")
        Optional<BookingEntity> findByIdForUpdate(@Param("id") Long id);

        @Query("""
                        SELECT b
                        FROM BookingEntity b
                        WHERE b.car.id = :carId
                        AND b.startTime <= :endTime
                        AND b.endTime >= :startTime
                        AND b.status  = 'PENDING'
                        """)
        List<BookingEntity> findBookingsOverlap(
                        @Param("carId") Long carId,
                        @Param("startTime") Date startTime,
                        @Param("endTime") Date endTime);

        List<BookingEntity> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(
                        Date startTime,
                        Date endTime);

        Optional<BookingEntity> findById(Long id);
}
