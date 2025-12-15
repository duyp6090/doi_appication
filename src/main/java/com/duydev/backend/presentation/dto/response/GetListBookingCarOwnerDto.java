package com.duydev.backend.presentation.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetListBookingCarOwnerDto {
    Long bookingId;
    CarResponseDto car;
    Date startTime;
    Date endTime;
    Double totalPrice;
    String status;
}
