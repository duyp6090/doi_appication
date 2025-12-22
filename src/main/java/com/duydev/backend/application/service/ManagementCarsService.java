package com.duydev.backend.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.duydev.backend.application.service.interfaceservice.IManagementCarsService;
import com.duydev.backend.domain.constant.SortConstant;
import com.duydev.backend.domain.enums.StatusBooking;
import com.duydev.backend.domain.model.BookingEntity;
import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.domain.model.LocationEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.BookingRepository;
import com.duydev.backend.domain.repositories.CarsRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.request.RequestGetListCarsDto;
import com.duydev.backend.presentation.dto.request.RequestLocationDto;
import com.duydev.backend.presentation.dto.request.RequestUpdateCarDto;
import com.duydev.backend.presentation.dto.response.CarResponseDto;
import com.duydev.backend.presentation.dto.response.InformationCarResponseDto;
import com.duydev.backend.presentation.dto.response.LocationCarResponseDto;
import com.duydev.backend.presentation.dto.response.PaginationDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;
import com.duydev.backend.presentation.dto.response.ReviewBookingResponseDto;
import com.duydev.backend.util.CloudinaryUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "ManagementCarsService")
@Service
@RequiredArgsConstructor
public class ManagementCarsService implements IManagementCarsService {
    private final CarsRepository carsRepository;

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final CloudinaryUtil cloudinaryUtil;

    @Override
    @Transactional
    public ResponseDto<String> createCar(RequestCreateCarDto requestCreateCarDto, List<MultipartFile> images) {
        try {
            // Step By Step

            // Get Owner by id
            User owner = userRepository.findById(requestCreateCarDto.getOwnerId())
                    .orElseThrow(() -> new AppException(EnumException.USER_NOT_FOUND));

            // 1. Create options to upload file
            Map<String, Object> options = Map.of(
                    "upload_preset", "save-img",
                    "quality", "auto",
                    "fetch_format", "auto");

            // 2. Upload file to cloudinary
            List<Map<String, Object>> results = cloudinaryUtil.bulkUpload(images, options);

            // 3. Create Json string to save public_id and url for each image
            Map<String, String> imagesUpload = new HashMap<>();
            for (Map<String, Object> imageInfo : results) {
                String publicId = (String) imageInfo.get("public_id");
                String url = (String) imageInfo.get("secure_url");

                imagesUpload.put(publicId, url);
            }

            String imagesJson = new ObjectMapper().writeValueAsString(imagesUpload);

            // 4. Create car entity and location and save
            RequestLocationDto requestLocationDto = requestCreateCarDto.getLocation();
            LocationEntity location = LocationEntity.builder()
                    .name(requestLocationDto.getName())
                    .province(requestLocationDto.getProvince())
                    .ward(requestLocationDto.getWard())
                    .latitude(requestLocationDto.getLatitude())
                    .longitude(requestLocationDto.getLongitude())
                    .build();

            CarsEntity car = CarsEntity.builder()
                    .user(owner)
                    .brand(requestCreateCarDto.getBrand())
                    .model(requestCreateCarDto.getModel())
                    .licensePlate(requestCreateCarDto.getLicensePlate())
                    .year(requestCreateCarDto.getYear())
                    .pricePerHour(requestCreateCarDto.getPricePerHour())
                    .location(location)
                    .images(imagesJson)
                    .build();

            carsRepository.save(car);
        } catch (Exception e) {
            throw new AppException(EnumException.CREATE_CAR_ERROR);
        }

        return ResponseDto.<String>builder()
                .message(List.of("Create car successfully"))
                .status(200)
                .build();
    }

    @Override
    public ResponseDto<String> updateCar(RequestUpdateCarDto requestUpdateCarDto, List<MultipartFile> images) {
        // Step by step
        // 1. Find car by id and check valid owner
        CarsEntity car = carsRepository.findById(requestUpdateCarDto.getCarId())
                .orElseThrow(() -> new AppException(EnumException.CAR_NOT_FOUND));
        if (!car.getUser().getId().equals(requestUpdateCarDto.getOwnerId())) {
            throw new AppException(EnumException.USER_NOT_FOUND);
        }

        // 2. Update fields if not null
        if (requestUpdateCarDto.getBrand() != null) {
            car.setBrand(requestUpdateCarDto.getBrand());
        }
        if (requestUpdateCarDto.getModel() != null) {
            car.setModel(requestUpdateCarDto.getModel());
        }
        if (requestUpdateCarDto.getLicensePlate() != null) {
            car.setLicensePlate(requestUpdateCarDto.getLicensePlate());
        }
        if (requestUpdateCarDto.getYear() != null) {
            car.setYear(requestUpdateCarDto.getYear());
        }
        if (requestUpdateCarDto.getPricePerHour() != null) {
            car.setPricePerHour(requestUpdateCarDto.getPricePerHour());
        }
        LocationEntity location = car.getLocation();
        if (location == null)
            location = new LocationEntity();
        if (requestUpdateCarDto.getNameLocation() != null) {
            location.setName(requestUpdateCarDto.getNameLocation());
        }
        if (requestUpdateCarDto.getProvince() != null) {
            location.setProvince(requestUpdateCarDto.getProvince());
        }
        if (requestUpdateCarDto.getWard() != null) {
            location.setWard(requestUpdateCarDto.getWard());
        }
        if (requestUpdateCarDto.getLatitude() != null) {
            location.setLatitude(requestUpdateCarDto.getLatitude());
        }
        if (requestUpdateCarDto.getLongitude() != null) {
            location.setLongitude(requestUpdateCarDto.getLongitude());
        }
        car.setLocation(location);

        // 2.1 Upload image if images is not null
        List<String> publishIds = requestUpdateCarDto.getPublishIds();
        if (images != null && !images.isEmpty() && publishIds != null && !publishIds.isEmpty()) {

            List<Map<String, Object>> results = cloudinaryUtil.reBulkUpload(images, publishIds);
            String imagesJson = car.getImages();
            try {
                Map<String, String> imagesUpload = new ObjectMapper().readValue(imagesJson,
                        new TypeReference<Map<String, String>>() {
                        });
                for (Map<String, Object> imageInfo : results) {
                    String publicId = (String) imageInfo.get("public_id");
                    String url = (String) imageInfo.get("secure_url");

                    imagesUpload.replace(publicId, url);
                }

                String updatedImagesJson = new ObjectMapper().writeValueAsString(imagesUpload);
                car.setImages(updatedImagesJson);
            } catch (Exception e) {
                log.error("Error parse json images: {}", e.getMessage());
                throw new AppException(EnumException.UPDATE_CAR_ERROR);
            }

        }

        // 3. Save car
        carsRepository.save(car);

        return ResponseDto.<String>builder()
                .message(List.of("Update car successfully"))
                .status(200)
                .build();
    }

    @Override
    public ResponseDto<String> deleteCar(Long carId, Long ownerId) {
        // Step by step
        // 1. Find car by id and check valid owner
        CarsEntity car = carsRepository.findById(carId)
                .orElseThrow(() -> new AppException(EnumException.CAR_NOT_FOUND));
        if (!car.getUser().getId().equals(ownerId)) {
            throw new AppException(EnumException.OWNER_HAS_NOT_CAR);
        }

        // 2. If car has booking in future with status confirmed cannot delete
        boolean hasFutureBooking = bookingRepository.existsByCarIdAndEndTimeAfterAndStatus(
                carId, new Date(System.currentTimeMillis()), StatusBooking.CONFIRMED);
        if (hasFutureBooking) {
            throw new AppException(EnumException.CAR_HAS_FUTURE_BOOKING);
        }

        // 3. Delete car
        carsRepository.delete(car);

        return ResponseDto.<String>builder()
                .message(List.of("Delete car successfully"))
                .status(200)
                .build();
    }

    @Override
    public ResultPaginationDto<List<CarResponseDto>> getListCars(RequestGetListCarsDto requestGetListCarsDto) {
        // Step by step
        // 1. Build pageable
        int page = requestGetListCarsDto.getPage() - 1;
        int size = requestGetListCarsDto.getSize();
        List<Sort.Order> orders = new ArrayList<>();
        String sortBy = requestGetListCarsDto.getSortBy();
        if (sortBy != null && !sortBy.isEmpty()) {
            String[] sortParams = sortBy.split(";");
            for (String sortParam : sortParams) {
                String[] fieldAndDirection = sortParam.split(",");
                if (fieldAndDirection.length == 2) {
                    String field = fieldAndDirection[0];
                    String direction = fieldAndDirection[1];
                    if (!SortConstant.SORT_FIELDS_MANAGEMENT_CAR.contains(field)) {
                        throw new AppException(EnumException.SORT_BY_INVALID);
                    }
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction), field);
                    orders.add(order);
                }
            }
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

        // 2. Query data
        Page<CarsEntity> carsPage = carsRepository.findListCars(requestGetListCarsDto, pageable);

        // 3. Map data to response dto
        PaginationDto paginationDto = PaginationDto.builder()
                .page(page + 1)
                .size(size)
                .totalPages(carsPage.getTotalPages())
                .totalElements(carsPage.getTotalElements())
                .build();

        List<CarResponseDto> carResponseDtos = carsPage.getContent().stream().map(
                (CarsEntity car) -> CarResponseDto.builder()
                        .id(car.getId())
                        .brand(car.getBrand())
                        .model(car.getModel())
                        .year(car.getYear())
                        .pricePerHour(car.getPricePerHour())
                        .images(car.getImages())
                        .build())
                .toList();

        return ResultPaginationDto.<List<CarResponseDto>>builder()
                .data(carResponseDtos)
                .message(List.of("Get list cars successfully"))
                .status(200)
                .pagination(paginationDto)
                .build();
    }

    @Override
    public ResponseDto<InformationCarResponseDto> getInformationCar(Long carId) {
        // Step by step
        // 1. Find car by id
        CarsEntity car = carsRepository.findOneCar(carId);
        if (car == null) {
            throw new AppException(EnumException.CAR_NOT_FOUND);
        }

        // 2. Map to response dto
        LocationCarResponseDto locationDto = LocationCarResponseDto.builder()
                .name(car.getLocation() != null ? car.getLocation().getName() : null)
                .province(car.getLocation() != null ? car.getLocation().getProvince() : null)
                .ward(car.getLocation() != null ? car.getLocation().getWard() : null)
                .latitude(car.getLocation() != null ? car.getLocation().getLatitude() : null)
                .longitude(car.getLocation() != null ? car.getLocation().getLongitude() : null)
                .build();

        List<BookingEntity> bookings = car.getBookings();
        List<ReviewBookingResponseDto> reviews = new ArrayList<>();
        for (BookingEntity booking : bookings) {
            if (booking.getReview() != null) {
                ReviewBookingResponseDto reviewDto = ReviewBookingResponseDto.builder()
                        .id(booking.getReview().getId())
                        .bookingId(booking.getId())
                        .rating(booking.getReview().getRating())
                        .comment(booking.getReview().getComment())
                        .createdAt(booking.getReview().getCreatedAt())
                        .customerId(booking.getCustomer().getId())
                        .customerName(booking.getCustomer().getUsername())
                        .build();
                reviews.add(reviewDto);
            }
        }

        InformationCarResponseDto informationCarResponseDto = InformationCarResponseDto.builder()
                .username(car.getUser().getUsername())
                .phoneNumber(car.getUser().getPhone())
                .brand(car.getBrand())
                .model(car.getModel())
                .licensePlate(car.getLicensePlate())
                .year(car.getYear())
                .pricePerHour(car.getPricePerHour())
                .location(locationDto)
                .images(car.getImages())
                .reviews(reviews)
                .build();

        return ResponseDto.<InformationCarResponseDto>builder()
                .data(informationCarResponseDto)
                .message(List.of("Get information car successfully"))
                .status(200)
                .build();
    }

}
