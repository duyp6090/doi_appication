package com.duydev.backend.presentation.dto.request;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestGetListBookingOwnerDto {
    @NotNull(message = "START_TIME_NOT_NULL")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime startTime;

    @NotNull(message = "END_TIME_NOT_NULL")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime endTime;
}
