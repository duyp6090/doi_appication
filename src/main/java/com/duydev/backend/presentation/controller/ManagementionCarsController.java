package com.duydev.backend.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duydev.backend.application.service.interfaceservice.IManagementCarsService;
import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j(topic = "ManagementionCarsController")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/managemention-cars")
public class ManagementionCarsController {

    private final IManagementCarsService managementCarsService;

    //@PreAuthorize("hasRole('ROLE_ADMIN') and hasAuthority('CREATE')")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<String>> createCar(@Valid @ModelAttribute RequestCreateCarDto car) {
        log.info("Creating car: {}", car);
        ResponseDto<String> response = managementCarsService.createCar(car);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
