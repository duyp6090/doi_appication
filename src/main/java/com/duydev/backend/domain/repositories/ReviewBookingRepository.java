package com.duydev.backend.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duydev.backend.domain.model.ReviewsEntity;

public interface ReviewBookingRepository extends JpaRepository<ReviewsEntity, Long> {

}
