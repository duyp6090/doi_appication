package com.duydev.backend.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestGetListCarsDto {
    @NotNull(message = "OWNER_ID_NOT_NULL")
    private Long ownerId;

    private String brand;
    private Integer year;
    private String province;
    private String ward;

    @JsonSetter(nulls = Nulls.SKIP)
    private Double minPrice = 0.0;

    @JsonSetter(nulls = Nulls.SKIP)
    private Double maxPrice = Double.MAX_VALUE;

    @Min(value = 1, message = "LIMIT_MIN_PAGE_1")
    @JsonSetter(nulls = Nulls.SKIP)
    private Integer page = 1;

    @Min(value = 1, message = "LIMIT_MIN_SIZE_1")
    @JsonSetter(nulls = Nulls.SKIP)
    private Integer size = 10;

    @Pattern(regexp = "^([a-zA-Z0-9_]+),(asc|desc)(;([a-zA-Z0-9_]+),(asc|desc))*$", message = "SORT_BY_INVALID")
    private String sortBy;
}
