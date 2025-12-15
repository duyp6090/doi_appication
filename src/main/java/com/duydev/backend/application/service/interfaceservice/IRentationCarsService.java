package com.duydev.backend.application.service.interfaceservice;

import java.util.List;

import com.duydev.backend.presentation.dto.request.RequestBookingCarDto;
import com.duydev.backend.presentation.dto.request.RequestGetCarsDto;
import com.duydev.backend.presentation.dto.request.RequestGetListBookingDto;
import com.duydev.backend.presentation.dto.request.RequestGetListBookingOwnerDto;
import com.duydev.backend.presentation.dto.response.CarsResponseDto;
import com.duydev.backend.presentation.dto.response.DetailBookingCarDto;
import com.duydev.backend.presentation.dto.response.GetListBookingCarOwnerDto;
import com.duydev.backend.presentation.dto.response.GetListBookingsCarDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;

public interface IRentationCarsService {
    ResultPaginationDto<List<CarsResponseDto>> findCars(RequestGetCarsDto request);

    ResponseDto<String> createBookingCar(RequestBookingCarDto request);

    ResponseDto<String> cancelBookingCar(Long bookingId);

    ResponseDto<String> confirmBookingCar(Long bookingId);

    ResponseDto<List<GetListBookingsCarDto>> getlistBookingsCar(RequestGetListBookingDto request);

    ResponseDto<DetailBookingCarDto> getDetailBookingCar(Long bookingId);

    ResponseDto<List<GetListBookingCarOwnerDto>> getListBookingCarOwner(RequestGetListBookingOwnerDto request);
}
