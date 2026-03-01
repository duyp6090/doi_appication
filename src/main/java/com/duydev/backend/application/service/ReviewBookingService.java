package com.duydev.backend.application.service;

import com.duydev.backend.application.service.interfaceservice.IReviewBooking;
import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import com.duydev.backend.domain.model.BookingEntity;
import com.duydev.backend.domain.model.ReviewsEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.BookingRepository;
import com.duydev.backend.domain.repositories.ReviewBookingRepository;
import com.duydev.backend.presentation.dto.request.RequestCreateBookingDto;
import com.duydev.backend.presentation.dto.request.RequestUpdateBookingDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic = "ReviewBookingServiceImpl")
@RequiredArgsConstructor
@Service
public class ReviewBookingService implements IReviewBooking {
    private final BookingRepository bookingRepository;
    private final ReviewBookingRepository reviewBookingRepository;

    @Override
    public ResponseDto<String> createReviewBookingCar(RequestCreateBookingDto request) {
        // Step by step
        // 1. Get booking by bookingId and check user has booking
        BookingEntity booking = checkUserHasBooking(request.getBookingId());

        // 2. Create review booking
        ReviewsEntity review = ReviewsEntity.builder()
                .booking(booking)
                .rating(request.getRating())
                .comment(request.getReviewText())
                .build();

        reviewBookingRepository.save(review);

        // 3. Save review booking to database
        return ResponseDto.<String>builder()
                .status(201)
                .message(List.of("Create review booking successfully"))
                .data("Review ID: " + review.getId())
                .build();
    }

    @Override
    public ResponseDto<String> updateReviewBookingCar(RequestUpdateBookingDto request) {
        // Step by step
        // 1. Get booking by bookingId and check user has booking
        checkUserHasBooking(request.getBookingId());

        // 2. Update review booking
        ReviewsEntity review = reviewBookingRepository.findById(request.getReviewId())
                .orElseThrow(() -> new AppException(EnumException.REVIEW_NOT_FOUND));

        if (request.getRating() != null) {
            review.setRating(request.getRating());
        }
        if (request.getReviewText() != null) {
            review.setComment(request.getReviewText());
        }
        reviewBookingRepository.save(review);

        // 3. Save review booking to database
        return ResponseDto.<String>builder()
                .status(200)
                .message(List.of("Update review booking successfully"))
                .data("Review ID: " + review.getId())
                .build();
    }

    @Override
    @Transactional
    public ResponseDto<String> deleteReviewBookingCar(Long bookingId, Long reviewId) {
        // Step by step
        // 1. Get booking by bookingId and check user has booking
        BookingEntity booking = checkUserHasBooking(bookingId);

        // 2. Delete review booking
        booking.setReview(null);

        // 3. Save changes to database
        return ResponseDto.<String>builder()
                .status(200)
                .message(List.of("Delete review booking successfully"))
                .data("Review ID: " + reviewId)
                .build();
    }

    private BookingEntity checkUserHasBooking(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(EnumException.BOOKING_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        if (!booking.getCustomer().getId().equals(currentUser.getId())) {
            throw new AppException(EnumException.USER_HAS_NOT_BOOKING);
        }

        return booking;
    }

}
