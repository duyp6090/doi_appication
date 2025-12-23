package com.duydev.backend.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duydev.backend.application.service.interfaceservice.IReviewBooking;
import com.duydev.backend.presentation.dto.request.RequestCreateBookingDto;
import com.duydev.backend.presentation.dto.request.RequestUpdateBookingDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j(topic = "ReviewBookingController")
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/review-booking")
@RestController
public class ReviewBookingController {
    private final IReviewBooking reviewBookingService;

    // Create ReviewBooking
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<String>> createReviewBookingCar(
            @RequestBody RequestCreateBookingDto request) {
        log.info("Creating review booking with request: {}", request);
        ResponseDto<String> response = reviewBookingService.createReviewBookingCar(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Update ReviewBooking
    @PatchMapping("/update")
    public ResponseEntity<ResponseDto<String>> updateReviewBookingCar(
            @RequestBody RequestUpdateBookingDto request) {
        log.info("Updating review booking with request: {}", request);
        ResponseDto<String> response = reviewBookingService.updateReviewBookingCar(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Delete ReviewBooking
    @DeleteMapping("/delete/{bookingId}/{reviewId}")
    public ResponseEntity<ResponseDto<String>> deleteReviewBookingCar(
            @PathVariable("bookingId") Long bookingId,
            @PathVariable("reviewId") Long reviewId) {
        log.info("Deleting review booking with bookingId: {} and reviewId: {}", bookingId, reviewId);
        ResponseDto<String> response = reviewBookingService.deleteReviewBookingCar(bookingId, reviewId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
