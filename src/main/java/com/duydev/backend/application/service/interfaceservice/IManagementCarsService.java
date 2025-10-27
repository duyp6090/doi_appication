package com.duydev.backend.application.service.interfaceservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.request.RequestGetListCarsDto;
import com.duydev.backend.presentation.dto.request.RequestUpdateCarDto;
import com.duydev.backend.presentation.dto.response.CarResponseDto;
import com.duydev.backend.presentation.dto.response.InformationCarResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;

public interface IManagementCarsService {
    public ResponseDto<String> createCar(RequestCreateCarDto requestCreateCarDto, List<MultipartFile> images);

    public ResponseDto<String> updateCar(RequestUpdateCarDto requestUpdateCarDto, List<MultipartFile> images);

    public ResponseDto<String> deleteCar(Long carId, Long ownerId);

    public ResultPaginationDto<List<CarResponseDto>> getListCars(RequestGetListCarsDto requestGetListCarsDto);

    public ResponseDto<InformationCarResponseDto> getInformationCar(Long carId);
}
