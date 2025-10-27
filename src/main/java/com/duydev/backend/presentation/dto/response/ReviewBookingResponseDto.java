package com.duydev.backend.presentation.dto.response;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewBookingResponseDto {
    private Long id;
    private Long bookingId;
    private Double rating;
    private String comment;
    private Date createdAt;
}
