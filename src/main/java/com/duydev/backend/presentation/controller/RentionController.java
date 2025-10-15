package com.duydev.backend.presentation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duydev.backend.application.service.interfaceservice.IRentationCarsService;
import com.duydev.backend.presentation.dto.request.RequestGetCarsDto;
import com.duydev.backend.presentation.dto.response.CarsResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j(topic = "RentionController")
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/rention")
@RestController
public class RentionController {

    private final IRentationCarsService rentationCarsService;

    // Find car
    @GetMapping("/cars")
    public ResponseEntity<ResultPaginationDto<List<CarsResponseDto>>> findCars(
            @ModelAttribute RequestGetCarsDto request) {
        log.info("Finding cars with criteria: {}", request);

        ResultPaginationDto<List<CarsResponseDto>> response = rentationCarsService.findCars(request);
        return ResponseEntity.ok(response);
    }

}
