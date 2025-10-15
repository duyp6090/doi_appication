package com.duydev.backend.presentation.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestGetCarsDto {
    private String brand;
    private Integer year;
    private String province;
    private String ward;
    private Double minPrice = 0.0;
    private Double maxPrice = Double.MAX_VALUE;
    private Double longitude;
    private Double latitude;

    @NotNull(message = "START_TIME_NOT_NULL")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "END_TIME_NOT_NULL")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    @Min(value = 1, message = "LIMIT_MIN_PAGE_1")
    int page = 1;

    @Min(value = 1, message = "LIMIT_MIN_SIZE_1")
    int size = 10;

    @Pattern(regexp = "^([a-zA-Z0-9_]+),(asc|desc)(\\|([a-zA-Z0-9_]+),(asc|desc))*$", message = "SORT_BY_INVALID")
    private String sortBy;
}
