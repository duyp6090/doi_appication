package com.duydev.backend.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.duydev.backend.application.service.interfaceservice.IRentationCarsService;
import com.duydev.backend.domain.constant.SortConstant;
import com.duydev.backend.domain.repositories.CarsRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestGetCarsDto;
import com.duydev.backend.presentation.dto.response.CarsResponseDto;
import com.duydev.backend.presentation.dto.response.PaginationDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "RentationCarsService")
@Service
@RequiredArgsConstructor
public class RentationCarsService implements IRentationCarsService {
    private final CarsRepository carsRepository;

    @Override
    public ResultPaginationDto<List<CarsResponseDto>> findCars(RequestGetCarsDto request) {
        // Step by Step
        // 1. Get list sort
        List<String> validSortFields = SortConstant.SORT_FIELDS_CAR;

        // 2. Create sort and pagination
        int page = request.getPage() - 1;
        int size = request.getSize();
        List<Sort.Order> orders = new ArrayList<>();
        String sortBy = request.getSortBy();
        if (sortBy != null) {
            String[] sortParameters = sortBy.split("\\|");
            for (String param : sortParameters) {
                String[] fieldAndDirect = param.split(",");
                String field = fieldAndDirect[0];
                String direction = fieldAndDirect[1];
                if (!validSortFields.contains(field)) {
                    throw new AppException(EnumException.SORT_BY_INVALID);
                }
                Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction), field);
                orders.add(order);
            }
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

        // 3. Call repository
        Page<CarsResponseDto> carsPage = carsRepository.findCars(request, pageable);

        // 4. Return response
        PaginationDto paginationDto = PaginationDto.builder()
                .page(page)
                .size(size)
                .totalPages(carsPage.getTotalPages())
                .totalElements(carsPage.getTotalElements())
                .build();

        return ResultPaginationDto.<List<CarsResponseDto>>builder()
                .data(carsPage.getContent())
                .pagination(paginationDto)
                .status(200)
                .message(List.of("Find cars success"))
                .build();

    }

}
