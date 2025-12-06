package com.duydev.backend.presentation.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duydev.backend.application.service.interfaceservice.IRentationCarsService;
import com.duydev.backend.presentation.dto.request.RequestBookingCarDto;
import com.duydev.backend.presentation.dto.request.RequestGetCarsDto;
import com.duydev.backend.presentation.dto.request.RequestGetListBookingDto;
import com.duydev.backend.presentation.dto.response.CarsResponseDto;
import com.duydev.backend.presentation.dto.response.DetailBookingCarDto;
import com.duydev.backend.presentation.dto.response.GetListBookingsCarDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j(topic = "RentionController")
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/rention")
@RestController
public class RentionController {

    private final IRentationCarsService rentationCarsService;

    @GetMapping("/cars")
    public ResponseEntity<ResultPaginationDto<List<CarsResponseDto>>> findCars(
            @Valid @ModelAttribute RequestGetCarsDto request) {
        log.info("Finding cars with criteria: {}", request);

        ResultPaginationDto<List<CarsResponseDto>> response = rentationCarsService.findCars(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('CUSTOMER') and hasAuthority('create')")
    @PostMapping("/booking")
    public ResponseEntity<ResponseDto<String>> createBooking(@RequestBody RequestBookingCarDto request) {
        log.info("Creating booking with request: {}", request);
        ResponseDto<String> response = rentationCarsService.createBookingCar(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('CUSTOMER') and hasAuthority('update')")
    @PatchMapping("/booking/{bookingId}/cancel")
    public ResponseEntity<ResponseDto<String>> cancelBooking(
            @PathVariable @NotNull(message = "BOOKING_ID_NOT_NULL") Long bookingId) {
        log.info("Cancelling booking with ID: {}", bookingId);
        ResponseDto<String> response = rentationCarsService.cancelBookingCar(bookingId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('OWNER') and hasAuthority('update')")
    @PatchMapping("/booking/{bookingId}/confirm")
    public ResponseEntity<ResponseDto<String>> confirmBooking(
            @PathVariable @NotNull(message = "BOOKING_ID_NOT_NULL") Long bookingId) {
        log.info("Confirming booking with ID: {}", bookingId);
        ResponseDto<String> response = rentationCarsService.confirmBookingCar(bookingId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/booking/{bookingId}/detail")
    public ResponseEntity<ResponseDto<DetailBookingCarDto>> getBookingDetailCar(
            @PathVariable @NotNull(message = "BOOKING_ID_NOT_NULL") Long bookingId) {
        log.info("Getting booking detail for ID: {}", bookingId);
        ResponseDto<DetailBookingCarDto> response = rentationCarsService.getDetailBookingCar(bookingId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/bookings")
    public ResponseEntity<ResponseDto<List<GetListBookingsCarDto>>> getListBookingsCar(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endTime) {
        RequestGetListBookingDto request = RequestGetListBookingDto.builder()
                .startTime(startTime)
                .endTime(endTime)
                .build();
        log.info("Getting list of bookings with criteria: {}", request);
        ResponseDto<List<GetListBookingsCarDto>> response = rentationCarsService.getlistBookingsCar(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
