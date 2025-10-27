package com.duydev.backend.presentation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.duydev.backend.application.service.interfaceservice.IManagementCarsService;
import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.request.RequestDeleteCarDto;
import com.duydev.backend.presentation.dto.request.RequestGetListCarsDto;
import com.duydev.backend.presentation.dto.request.RequestUpdateCarDto;
import com.duydev.backend.presentation.dto.response.CarResponseDto;
import com.duydev.backend.presentation.dto.response.InformationCarResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j(topic = "ManagementionCarsController")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/managemention-cars")
public class ManagementionCarsController {

    private final IManagementCarsService managementCarsService;

    @PreAuthorize("hasRole('OWNER') and hasAuthority('create')")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<String>> createCar(
            @Valid @RequestPart RequestCreateCarDto car,
            @RequestPart("images") @NotNull(message = "IMAGES_NOT_NULL") @NotEmpty(message = "IMAGES_NOT_EMPTY") List<MultipartFile> images) {
        log.info("Creating car: {}", car);
        ResponseDto<String> response = managementCarsService.createCar(car, images);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('OWNER') and hasAuthority('update')")
    @PatchMapping("/update")
    public ResponseEntity<ResponseDto<String>> updateCar(
            @Valid @RequestPart RequestUpdateCarDto car,
            @RequestPart("images") List<MultipartFile> images) {
        log.info("Updating car: {}", car);
        ResponseDto<String> response = managementCarsService.updateCar(car, images);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('OWNER') and hasAuthority('delete')")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<String>> deleteCar(
            @Valid @RequestBody RequestDeleteCarDto request) {
        log.info("Deleting car with id: {}", request.getCarId());
        ResponseDto<String> response = managementCarsService.deleteCar(request.getCarId(), request.getOwnerId());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<ResultPaginationDto<List<CarResponseDto>>> getListCars(
            @Valid @ModelAttribute RequestGetListCarsDto request) {
        ResultPaginationDto<List<CarResponseDto>> response = managementCarsService.getListCars(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<InformationCarResponseDto>> getInformationCar(@PathVariable Long id) {
        ResponseDto<InformationCarResponseDto> responseDto = managementCarsService.getInformationCar(id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

}
