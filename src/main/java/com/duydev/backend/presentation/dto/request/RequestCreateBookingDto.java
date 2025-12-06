package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateBookingDto {
    @NotNull(message = "BOOKING_ID_NOT_NULL")
    Long bookingId;

    @NotNull(message = "RATING_NOT_NULL")
    Double rating;

    String reviewText;
}
