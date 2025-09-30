package com.duydev.backend.application.service.interfaceservice;

import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;

public interface IManagementCarsService {
    public ResponseDto<String> createCar(RequestCreateCarDto requestCreateCarDto);
}
