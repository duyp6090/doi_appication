package com.duydev.backend.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.duydev.backend.application.mapper.CarsResponseProjection;
import com.duydev.backend.application.service.interfaceservice.IRentalRequestProducer;
import com.duydev.backend.application.service.interfaceservice.IRentationCarsService;
import com.duydev.backend.domain.constant.SortConstant;
import com.duydev.backend.domain.enums.StatusBooking;
import com.duydev.backend.domain.model.BookingEntity;
import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.BookingRepository;
import com.duydev.backend.domain.repositories.CarsRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestBookingCarDto;
import com.duydev.backend.presentation.dto.request.RequestGetCarsDto;
import com.duydev.backend.presentation.dto.request.RequestGetListBookingDto;
import com.duydev.backend.presentation.dto.request.RequestGetListBookingOwnerDto;
import com.duydev.backend.presentation.dto.response.CarResponseDto;
import com.duydev.backend.presentation.dto.response.CarsResponseDto;
import com.duydev.backend.presentation.dto.response.DetailBookingCarDto;
import com.duydev.backend.presentation.dto.response.GetListBookingCarOwnerDto;
import com.duydev.backend.presentation.dto.response.GetListBookingsCarDto;
import com.duydev.backend.presentation.dto.response.PaginationDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;
import com.duydev.backend.presentation.dto.response.ReviewBookingResponseDto;
import com.duydev.backend.util.UserInformationUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "RentationCarsService")
@Service
@RequiredArgsConstructor
public class RentationCarsService implements IRentationCarsService {
        private final CarsRepository carsRepository;

        private final UserRepository userRepository;

        private final BookingRepository bookingRepository;

        private final IRentalRequestProducer rentalRequestProducer;

        private final UserInformationUtil userInformationUtil;

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
                if (sortBy != null && sortBy.length() != 0) {
                        String[] sortParameters = sortBy.split(";");
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
                Page<CarsResponseProjection> carsPage = carsRepository.findCars(
                                request.getBrand(),
                                request.getYear(),
                                request.getProvince(),
                                request.getWard(),
                                request.getMinPrice(),
                                request.getMaxPrice(),
                                request.getLongitude(),
                                request.getLatitude(),
                                Date.from(request.getStartTime()
                                                .toInstant()),
                                Date.from(request.getEndTime()
                                                .toInstant()),
                                pageable);

                // Convert to CarsResponseDto
                List<CarsResponseDto> carsResponseDtos = new ArrayList<>();
                for (CarsResponseProjection projection : carsPage.getContent()) {
                        CarsResponseDto dto = new CarsResponseDto(
                                        projection.getId(),
                                        projection.getBrand(),
                                        projection.getModel(),
                                        projection.getYear(),
                                        projection.getPricePerHour(),
                                        projection.getImages(),
                                        projection.getDistance(),
                                        projection.getLocationName(),
                                        projection.getProvince(),
                                        projection.getWard(),
                                        projection.getLongitude(),
                                        projection.getLatitude());
                        carsResponseDtos.add(dto);
                }

                // 4. Return response
                PaginationDto paginationDto = PaginationDto.builder()
                                .page(page + 1)
                                .size(size)
                                .totalPages(carsPage.getTotalPages())
                                .totalElements(carsPage.getTotalElements())
                                .build();

                return ResultPaginationDto.<List<CarsResponseDto>>builder()
                                .data(carsResponseDtos)
                                .pagination(paginationDto)
                                .status(200)
                                .message(List.of("Find cars success"))
                                .build();
        }

        @Override
        public ResponseDto<String> createBookingCar(RequestBookingCarDto request) {
                // Step by step
                // 1. Check car is exist and available in time range
                CarsEntity car = carsRepository.findById(request.getCarId())
                                .orElseThrow(() -> new AppException(EnumException.CAR_NOT_FOUND));

                User user = userRepository.findById(request.getCustomerId())
                                .orElseThrow(() -> new AppException(EnumException.USER_NOT_FOUND));

                boolean isValidBookingTime = carsRepository.validBookingTime(
                                request.getCarId(),
                                Date.from(request.getStartTime()
                                                .toInstant()),
                                Date.from(request.getEndTime()
                                                .toInstant()),
                                user.getId());
                if (!isValidBookingTime) {
                        throw new AppException(EnumException.CAR_HAS_FUTURE_BOOKING);
                }

                // 2. Create booking car
                BookingEntity booking = BookingEntity.builder()
                                .car(car)
                                .customer(user)
                                .startTime(Date.from(request.getStartTime()
                                                .toInstant()))
                                .endTime(Date.from(request.getEndTime()
                                                .toInstant()))
                                .totalPrice(request.getTotalPrice())
                                .status(StatusBooking.PENDING)
                                .build();

                booking = bookingRepository.save(booking);

                // Send to message queue
                rentalRequestProducer.sendRequestRental(String.valueOf(booking.getId()));

                // 3. Return response
                return ResponseDto.<String>builder()
                                .data("Booking car success")
                                .status(200)
                                .message(List.of("Booking car success"))
                                .build();
        }

        @Override
        @Transactional
        public ResponseDto<String> cancelBookingCar(Long bookingId) {
                // Step by step
                // 1. Get booking record and lock row for update
                BookingEntity booking = bookingRepository.findByIdForUpdate(bookingId)
                                .orElseThrow(() -> new AppException(EnumException.BOOKING_NOT_FOUND));

                // 2. Validate booking record belong to user
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User currentUser = (User) authentication.getPrincipal();

                if (!booking.getCustomer().getId().equals(currentUser.getId())) {
                        throw new AppException(EnumException.USER_HAS_NOT_CAR);
                }

                // 3. Cancel booking if status is not COMPLETED or CANCELED
                if (booking.getStatus() == StatusBooking.COMPLETED) {
                        throw new AppException(EnumException.CAN_NOT_CANCEL_BOOKING);
                }
                booking.setStatus(StatusBooking.CANCELLED);

                return ResponseDto.<String>builder()
                                .data("Call api successfully")
                                .status(200)
                                .message(List.of("Cancel booking success"))
                                .build();
        }

        @Override
        @Transactional
        public ResponseDto<String> confirmBookingCar(Long bookingId) {
                // Step by step
                // 1. Get booking record and lock row
                BookingEntity booking = bookingRepository.findByIdForUpdate(bookingId)
                                .orElseThrow(() -> new AppException(EnumException.BOOKING_NOT_FOUND));

                // 2. Validate car belong to owner
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User currentUser = (User) authentication.getPrincipal();

                Long ownerId = booking.getCar().getUser().getId();
                if (!ownerId.equals(currentUser.getId())) {
                        throw new AppException(EnumException.OWNER_HAS_NOT_CAR);
                }

                // 3. Confirmed booking if status booking is pending
                if (booking.getStatus() != StatusBooking.PENDING) {
                        throw new AppException(EnumException.CAN_NOT_CONFIRM_BOOKING);
                }

                // 4. Set status confirmed
                booking.setStatus(StatusBooking.CONFIRMED);
                bookingRepository.save(booking);

                // 5. Canceled others booking that has status pending and overlap time
                List<BookingEntity> bookings = bookingRepository.findBookingsOverlap(booking.getCar().getId(),
                                booking.getStartTime(), booking.getEndTime());
                for (BookingEntity b : bookings) {
                        if (b.getStatus() == StatusBooking.PENDING) {
                                b.setStatus(StatusBooking.CANCELLED);
                        }
                }
                bookingRepository.saveAll(bookings);

                // 5. Return response
                return ResponseDto.<String>builder()
                                .data("Call api successfully")
                                .status(200)
                                .message(List.of("Confirm booking success"))
                                .build();
        }

        @Override
        public ResponseDto<List<GetListBookingsCarDto>> getlistBookingsCar(RequestGetListBookingDto request) {
                // Step by step
                // 1. Get list booking car follow start and end time
                Date startTime = Date.from(request.getStartTime().toInstant());
                Date endTime = Date.from(request.getEndTime().toInstant());

                // Get current user
                User currentUser = userInformationUtil.getCurrentUser();
                List<BookingEntity> bookingsEntity = bookingRepository
                                .findByCustomer_IdAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(
                                                currentUser.getId(), startTime, endTime);

                // 2. Convert to GetListBookingsCarDto
                List<GetListBookingsCarDto> bookingsDto = bookingsEntity.stream().map(
                                (BookingEntity bookingEntity) -> {
                                        CarResponseDto carDto = CarResponseDto.builder()
                                                        .id(bookingEntity.getCar().getId())
                                                        .brand(bookingEntity.getCar().getBrand())
                                                        .model(bookingEntity.getCar().getModel())
                                                        .year(bookingEntity.getCar().getYear())
                                                        .pricePerHour(bookingEntity.getCar().getPricePerHour())
                                                        .images(bookingEntity.getCar().getImages())
                                                        .build();

                                        return GetListBookingsCarDto.builder()
                                                        .bookingId(bookingEntity.getId())
                                                        .car(carDto)
                                                        .startTime(bookingEntity.getStartTime())
                                                        .endTime(bookingEntity.getEndTime())
                                                        .totalPrice(bookingEntity.getTotalPrice())
                                                        .status(bookingEntity.getStatus().name())
                                                        .build();
                                }).toList();

                // 3. Return response
                return ResponseDto.<List<GetListBookingsCarDto>>builder()
                                .data(bookingsDto)
                                .status(200)
                                .message(List.of("Get list bookings car success"))
                                .build();
        }

        @Override
        public ResponseDto<DetailBookingCarDto> getDetailBookingCar(Long bookingId) {
                // Step by step
                // 1. Get booking record
                BookingEntity booking = bookingRepository.findById(bookingId)
                                .orElseThrow(() -> new AppException(EnumException.BOOKING_NOT_FOUND));

                // Check booking belong to current user
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                User currentUser = (User) authentication.getPrincipal();
                if (!booking.getCustomer().getId().equals(currentUser.getId())) {
                        throw new AppException(EnumException.USER_HAS_NOT_BOOKING);
                }

                // 2. Convert to DetailBookingCarDto
                // Create ReviewBookingResponseDto
                ReviewBookingResponseDto reviewDto = ReviewBookingResponseDto.builder()
                                .id(booking.getReview() != null ? booking.getReview().getId() : null)
                                .rating(booking.getReview() != null ? booking.getReview().getRating() : null)
                                .comment(booking.getReview() != null ? booking.getReview().getComment() : null)
                                .createdAt(booking.getReview() != null ? booking.getReview().getCreatedAt() : null)
                                .build();

                // Create CarResponseDto
                CarResponseDto carDto = CarResponseDto.builder()
                                .id(booking.getCar().getId())
                                .brand(booking.getCar().getBrand())
                                .model(booking.getCar().getModel())
                                .year(booking.getCar().getYear())
                                .pricePerHour(booking.getCar().getPricePerHour())
                                .images(booking.getCar().getImages())
                                .build();

                // Create DetailBookingCarDto
                DetailBookingCarDto detailDto = DetailBookingCarDto.builder()
                                .car(carDto)
                                .startTime(booking.getStartTime())
                                .endTime(booking.getEndTime())
                                .totalPrice(booking.getTotalPrice())
                                .status(booking.getStatus().name())
                                .review(reviewDto)
                                .build();

                // 3. Return response
                return ResponseDto.<DetailBookingCarDto>builder()
                                .data(detailDto)
                                .status(200)
                                .message(List.of("Get detail booking car success"))
                                .build();
        }

        @Override
        public ResponseDto<List<GetListBookingCarOwnerDto>> getListBookingCarOwner(
                        RequestGetListBookingOwnerDto request) {
                // Step by step to get list booking for owner
                // 1. Get current user
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User currentUser = (User) authentication.getPrincipal();
                Long userId = currentUser.getId();

                Date startTime = Date.from(request.getStartTime().toInstant());
                Date endTime = Date.from(request.getEndTime().toInstant());

                // 2. Get list booking
                List<CarsEntity> cars = carsRepository.findCarsAndGetBookingsByUserId(userId, startTime, endTime);

                // 3. Convert to DTO and return response
                List<GetListBookingCarOwnerDto> bookingCarOwnerDtos = cars.stream().flatMap(
                                (CarsEntity car) -> {
                                        return car.getBookings().stream()
                                                        .map((BookingEntity booking) -> {
                                                                CarResponseDto carDto = CarResponseDto.builder()
                                                                                .id(car.getId())
                                                                                .brand(car.getBrand())
                                                                                .model(car.getModel())
                                                                                .year(car.getYear())
                                                                                .pricePerHour(car.getPricePerHour())
                                                                                .images(car.getImages())
                                                                                .build();
                                                                return GetListBookingCarOwnerDto.builder()
                                                                                .bookingId(booking.getId())
                                                                                .customerName(booking.getCustomer()
                                                                                                .getUsername())
                                                                                .customerPhone(booking.getCustomer()
                                                                                                .getPhone())
                                                                                .car(carDto)
                                                                                .startTime(booking.getStartTime())
                                                                                .endTime(booking.getEndTime())
                                                                                .totalPrice(booking.getTotalPrice())
                                                                                .status(booking.getStatus().name())
                                                                                .build();
                                                        });

                                }).toList();

                return ResponseDto.<List<GetListBookingCarOwnerDto>>builder()
                                .data(bookingCarOwnerDtos)
                                .status(200)
                                .message(List.of("Get list booking car owner success"))
                                .build();
        }

}
