package com.duydev.backend.presentation.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetListBookingCarOwnerDto {
    private Long bookingId;
    private String customerName;
    private String customerPhone;
    private CarResponseDto car;
    private Date startTime;
    private Date endTime;
    private Double totalPrice;
    private String status;
}
