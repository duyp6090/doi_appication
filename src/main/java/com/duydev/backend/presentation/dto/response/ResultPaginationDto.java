package com.duydev.backend.presentation.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ResultPaginationDto<T> extends ResponseDto<T> {
    private PaginationDto pagination;
}
