package com.duydev.backend.domain.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duydev.backend.domain.model.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    boolean existsByCarIdAndEndTimeAfterAndStatus(Long carId, Date time, String status);
}
