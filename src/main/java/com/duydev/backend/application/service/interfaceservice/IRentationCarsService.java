package com.duydev.backend.application.service.interfaceservice;

import java.util.List;

import com.duydev.backend.presentation.dto.request.RequestGetCarsDto;
import com.duydev.backend.presentation.dto.response.CarsResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;

public interface IRentationCarsService {
    ResultPaginationDto<List<CarsResponseDto>> findCars(RequestGetCarsDto request);
}
