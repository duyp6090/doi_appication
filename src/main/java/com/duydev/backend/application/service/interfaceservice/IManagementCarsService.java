package com.duydev.backend.application.service.interfaceservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;

public interface IManagementCarsService {
    public ResponseDto<String> createCar(RequestCreateCarDto requestCreateCarDto, List<MultipartFile> images);
}
