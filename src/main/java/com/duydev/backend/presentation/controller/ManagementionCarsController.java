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
import com.duydev.backend.presentation.dto.request.RequestUpdateCarDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;

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
    @PostMapping("/update")
    public ResponseEntity<ResponseDto<String>> updateCar(
            @Valid @RequestPart RequestUpdateCarDto car,
            @RequestPart("images") List<MultipartFile> images) {
        log.info("Updating car: {}", car);
        ResponseDto<String> response = managementCarsService.updateCar(car, images);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('OWNER') and hasAuthority('delete')")
    @PostMapping("/delete")
    public ResponseEntity<ResponseDto<String>> deleteCar(
            @Valid @RequestPart Long carId,
            @RequestPart Long ownerId) {
        log.info("Deleting car with id: {}", carId);
        ResponseDto<String> response = managementCarsService.deleteCar(carId, ownerId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
