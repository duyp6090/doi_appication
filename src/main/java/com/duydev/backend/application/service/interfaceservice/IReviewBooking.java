package com.duydev.backend.application.service.interfaceservice;

import com.duydev.backend.presentation.dto.request.RequestCreateBookingDto;
import com.duydev.backend.presentation.dto.request.RequestUpdateBookingDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;

public interface IReviewBooking {
    // Create review Booking
    ResponseDto<String> createReviewBookingCar(RequestCreateBookingDto request);

    // Update review Booking
    ResponseDto<String> updateReviewBookingCar(RequestUpdateBookingDto request);

    // Delete review Booking
    ResponseDto<String> deleteReviewBookingCar(Long bookingId, Long reviewId);
}
