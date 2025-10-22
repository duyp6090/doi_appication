package com.duydev.backend.presentation.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBookingCarDto {
    @NotNull(message = "CAR_ID_NOT_NULL")
    private Long carId;
    @NotNull(message = "CUSTOMER_ID_NOT_NULL")
    private Long customerId;
    @NotNull(message = "START_TIME_NOT_NULL")
    private Date startTime;
    @NotNull(message = "END_TIME_NOT_NULL")
    private Date endTime;
    @NotNull(message = "TOTAL_PRICE_NOT_NULL")
    private Double totalPrice;
}
