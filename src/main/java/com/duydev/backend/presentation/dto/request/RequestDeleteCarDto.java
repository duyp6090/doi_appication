package com.duydev.backend.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDeleteCarDto {
    @NotNull(message = "CAR_ID_NOT_NULL")
    private Long carId;
    @NotNull(message = "OWNER_ID_NOT_NULL")
    private Long ownerId;
}
