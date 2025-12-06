package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateBookingDto {
    @NotNull(message = "BOOKING_ID_NOT_NULL")
    Long bookingId;

    @NotNull(message = "REVIEW_ID_NOT_NULL")
    Long reviewId;

    Double rating;

    String reviewText;
}
