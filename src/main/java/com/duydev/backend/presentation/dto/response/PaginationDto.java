package com.duydev.backend.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaginationDto {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
