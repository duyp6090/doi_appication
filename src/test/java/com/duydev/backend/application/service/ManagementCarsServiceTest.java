package com.duydev.backend.application.service;

import com.duydev.backend.domain.constant.SortConstant;
import com.duydev.backend.domain.enums.StatusBooking;
import com.duydev.backend.domain.model.*;
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
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResultPaginationDto;
import com.duydev.backend.util.CloudinaryUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ManagementCarsService Unit Tests")
class ManagementCarsServiceTest {

    @Mock
    private CarsRepository carsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CloudinaryUtil cloudinaryUtil;

    @InjectMocks
    private ManagementCarsService managementCarsService;

    @Captor
    private ArgumentCaptor<CarsEntity> carEntityCaptor;

    // Test fixtures
    private User testOwner;
    private CarsEntity testCar;
    private RequestCreateCarDto createCarRequest;
    private List<MultipartFile> mockImages;

    @BeforeEach
    void setUp() {
        // Initialize test fixtures
        testOwner = User.builder()
                .username("testOwner")
                .phone("0123456789")
                .build();
        testOwner.setId(1L);

        LocationEntity testLocation = LocationEntity.builder()
                .name("Test Location")
                .province("Ho Chi Minh")
                .ward("District 1")
                .latitude(10.762622)
                .longitude(106.660172)
                .build();
        testLocation.setId(1L);

        testCar = CarsEntity.builder()
                .user(testOwner)
                .brand("Toyota")
                .model("Camry")
                .licensePlate("51A-12345")
                .year(2022)
                .pricePerHour(BigDecimal.valueOf(100000))
                .location(testLocation)
                .images("{\"img1\":\"url1\",\"img2\":\"url2\"}")
                .bookings(new ArrayList<>())
                .build();
        testCar.setId(1L);

        RequestLocationDto locationDto = RequestLocationDto.builder()
                .name("Test Location")
                .province("Ho Chi Minh")
                .ward("District 1")
                .latitude(10.762622)
                .longitude(106.660172)
                .build();

        createCarRequest = RequestCreateCarDto.builder()
                .ownerId(1L)
                .brand("Toyota")
                .model("Camry")
                .licensePlate("51A-12345")
                .year(2022)
                .pricePerHour(BigDecimal.valueOf(100000))
                .location(locationDto)
                .build();

        // Mock MultipartFile list
        MultipartFile mockFile = mock(MultipartFile.class);
        mockImages = List.of(mockFile, mockFile);
    }

    // ==================== CREATE CAR TESTS ====================

    @Nested
    @DisplayName("createCar() Tests")
    class CreateCarTests {

        @Test
        @DisplayName("Should create car successfully when all inputs are valid")
        void createCar_WithValidInputs_ShouldReturnSuccessResponse() {
            // Arrange
            given(userRepository.findById(1L)).willReturn(Optional.of(testOwner));

            List<Map<String, Object>> uploadResults = List.of(
                    Map.of("public_id", "img1", "secure_url", "https://example.com/img1.jpg"),
                    Map.of("public_id", "img2", "secure_url", "https://example.com/img2.jpg")
            );
            given(cloudinaryUtil.bulkUpload(anyList(), anyMap())).willReturn(uploadResults);
            given(carsRepository.save(any(CarsEntity.class))).willAnswer(invocation -> invocation.getArgument(0));

            // Act
            ResponseDto<String> response = managementCarsService.createCar(createCarRequest, mockImages);

            // Assert
            assertThat(response).isNotNull();
            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(response.getMessage()).contains("Create car successfully");

            // Verify interactions
            then(userRepository).should(times(1)).findById(1L);
            then(cloudinaryUtil).should(times(1)).bulkUpload(anyList(), anyMap());
            then(carsRepository).should(times(1)).save(carEntityCaptor.capture());

            // Verify captured entity
            CarsEntity savedCar = carEntityCaptor.getValue();
            assertThat(savedCar.getBrand()).isEqualTo("Toyota");
            assertThat(savedCar.getUser()).isEqualTo(testOwner);
            assertThat(savedCar.getLocation().getProvince()).isEqualTo("Ho Chi Minh");
        }

        @Test
        @DisplayName("Should throw AppException when owner not found")
        void createCar_WhenOwnerNotFound_ShouldThrowAppException() {
            // Arrange
            given(userRepository.findById(anyLong())).willReturn(Optional.empty());

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.createCar(createCarRequest, mockImages))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.CREATE_CAR_ERROR);

            // Verify no further interactions
            then(cloudinaryUtil).shouldHaveNoInteractions();
            then(carsRepository).shouldHaveNoInteractions();
        }

        @Test
        @DisplayName("Should throw AppException when cloudinary upload fails")
        void createCar_WhenCloudinaryUploadFails_ShouldThrowAppException() {
            // Arrange
            given(userRepository.findById(1L)).willReturn(Optional.of(testOwner));
            given(cloudinaryUtil.bulkUpload(anyList(), anyMap()))
                    .willThrow(new RuntimeException("Upload failed"));

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.createCar(createCarRequest, mockImages))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.CREATE_CAR_ERROR);

            then(carsRepository).shouldHaveNoInteractions();
        }
    }

    // ==================== UPDATE CAR TESTS ====================

    @Nested
    @DisplayName("updateCar() Tests")
    class UpdateCarTests {

        private RequestUpdateCarDto updateCarRequest;

        @BeforeEach
        void setUpUpdateRequest() {
            updateCarRequest = RequestUpdateCarDto.builder()
                    .carId(1L)
                    .ownerId(1L)
                    .brand("Honda")
                    .model("Civic")
                    .pricePerHour(BigDecimal.valueOf(150000))
                    .build();
        }

        @Test
        @DisplayName("Should update car successfully with partial fields")
        void updateCar_WithPartialFields_ShouldUpdateOnlyProvidedFields() {
            // Arrange
            given(carsRepository.findById(1L)).willReturn(Optional.of(testCar));
            given(carsRepository.save(any(CarsEntity.class))).willAnswer(inv -> inv.getArgument(0));

            // Act
            ResponseDto<String> response = managementCarsService.updateCar(updateCarRequest, null);

            // Assert
            assertThat(response.getStatus()).isEqualTo(200);

            then(carsRepository).should().save(carEntityCaptor.capture());
            CarsEntity updatedCar = carEntityCaptor.getValue();

            assertThat(updatedCar.getBrand()).isEqualTo("Honda");
            assertThat(updatedCar.getModel()).isEqualTo("Civic");
            assertThat(updatedCar.getLicensePlate()).isEqualTo("51A-12345"); // Unchanged
        }

        @Test
        @DisplayName("Should update car with new images when provided")
        void updateCar_WithNewImages_ShouldUpdateImages() {
            // Arrange
            updateCarRequest.setPublishIds(List.of("img1", "img2"));

            given(carsRepository.findById(1L)).willReturn(Optional.of(testCar));

            List<Map<String, Object>> reuploadResults = List.of(
                    Map.of("public_id", "img1", "secure_url", "https://example.com/new_img1.jpg"),
                    Map.of("public_id", "img2", "secure_url", "https://example.com/new_img2.jpg")
            );
            given(cloudinaryUtil.reBulkUpload(anyList(), anyList())).willReturn(reuploadResults);
            given(carsRepository.save(any(CarsEntity.class))).willAnswer(inv -> inv.getArgument(0));

            // Act
            ResponseDto<String> response = managementCarsService.updateCar(updateCarRequest, mockImages);

            // Assert
            assertThat(response.getStatus()).isEqualTo(200);
            then(cloudinaryUtil).should(times(1)).reBulkUpload(anyList(), anyList());
        }

        @Test
        @DisplayName("Should throw AppException when car not found")
        void updateCar_WhenCarNotFound_ShouldThrowAppException() {
            // Arrange
            given(carsRepository.findById(anyLong())).willReturn(Optional.empty());

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.updateCar(updateCarRequest, null))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.CAR_NOT_FOUND);
        }

        @Test
        @DisplayName("Should throw AppException when owner does not match")
        void updateCar_WhenOwnerMismatch_ShouldThrowAppException() {
            // Arrange
            updateCarRequest.setOwnerId(999L); // Different owner
            given(carsRepository.findById(1L)).willReturn(Optional.of(testCar));

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.updateCar(updateCarRequest, null))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.USER_NOT_FOUND);
        }

        @Test
        @DisplayName("Should handle null location gracefully")
        void updateCar_WhenCarHasNoLocation_ShouldCreateNewLocation() {
            // Arrange
            testCar.setLocation(null);
            updateCarRequest.setNameLocation("New Location");
            updateCarRequest.setProvince("Ha Noi");

            given(carsRepository.findById(1L)).willReturn(Optional.of(testCar));
            given(carsRepository.save(any(CarsEntity.class))).willAnswer(inv -> inv.getArgument(0));

            // Act
            ResponseDto<String> response = managementCarsService.updateCar(updateCarRequest, null);

            // Assert
            then(carsRepository).should().save(carEntityCaptor.capture());
            CarsEntity savedCar = carEntityCaptor.getValue();

            assertThat(savedCar.getLocation()).isNotNull();
            assertThat(savedCar.getLocation().getName()).isEqualTo("New Location");
            assertThat(savedCar.getLocation().getProvince()).isEqualTo("Ha Noi");
        }
    }

    // ==================== DELETE CAR TESTS ====================

    @Nested
    @DisplayName("deleteCar() Tests")
    class DeleteCarTests {

        @Test
        @DisplayName("Should delete car successfully when no future bookings exist")
        void deleteCar_WithNoFutureBookings_ShouldDeleteSuccessfully() {
            // Arrange
            given(carsRepository.findById(1L)).willReturn(Optional.of(testCar));
            given(bookingRepository.existsByCarIdAndEndTimeAfterAndStatus(
                    eq(1L), any(Date.class), eq(StatusBooking.CONFIRMED)))
                    .willReturn(false);
            willDoNothing().given(carsRepository).delete(any(CarsEntity.class));

            // Act
            ResponseDto<String> response = managementCarsService.deleteCar(1L, 1L);

            // Assert
            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(response.getMessage()).contains("Delete car successfully");
            then(carsRepository).should(times(1)).delete(testCar);
        }

        @Test
        @DisplayName("Should throw AppException when car has future confirmed bookings")
        void deleteCar_WithFutureBookings_ShouldThrowAppException() {
            // Arrange
            given(carsRepository.findById(1L)).willReturn(Optional.of(testCar));
            given(bookingRepository.existsByCarIdAndEndTimeAfterAndStatus(
                    eq(1L), any(Date.class), eq(StatusBooking.CONFIRMED)))
                    .willReturn(true);

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.deleteCar(1L, 1L))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.CAR_HAS_FUTURE_BOOKING);

            then(carsRepository).should(never()).delete(any());
        }

        @Test
        @DisplayName("Should throw AppException when owner does not own the car")
        void deleteCar_WhenOwnerMismatch_ShouldThrowAppException() {
            // Arrange
            given(carsRepository.findById(1L)).willReturn(Optional.of(testCar));

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.deleteCar(1L, 999L))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.OWNER_HAS_NOT_CAR);
        }

        @Test
        @DisplayName("Should throw AppException when car not found")
        void deleteCar_WhenCarNotFound_ShouldThrowAppException() {
            // Arrange
            given(carsRepository.findById(anyLong())).willReturn(Optional.empty());

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.deleteCar(999L, 1L))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.CAR_NOT_FOUND);
        }
    }

    // ==================== GET LIST CARS TESTS ====================

    @Nested
    @DisplayName("getListCars() Tests")
    class GetListCarsTests {

        @Test
        @DisplayName("Should return paginated list of cars successfully")
        void getListCars_WithValidRequest_ShouldReturnPaginatedResult() {
            // Arrange
            RequestGetListCarsDto request = RequestGetListCarsDto.builder()
                    .page(1)
                    .size(10)
                    .build();

            List<CarsEntity> carsList = List.of(testCar);
            Page<CarsEntity> carsPage = new PageImpl<>(carsList);

            given(carsRepository.findListCars(any(RequestGetListCarsDto.class), any(Pageable.class)))
                    .willReturn(carsPage);

            // Act
            ResultPaginationDto<List<CarResponseDto>> result = managementCarsService.getListCars(request);

            // Assert
            assertThat(result.getStatus()).isEqualTo(200);
            assertThat(result.getData()).hasSize(1);
            assertThat(result.getPagination().getPage()).isEqualTo(1);
            assertThat(result.getPagination().getSize()).isEqualTo(10);

            CarResponseDto carDto = result.getData().get(0);
            assertThat(carDto.getBrand()).isEqualTo("Toyota");
            assertThat(carDto.getModel()).isEqualTo("Camry");
        }

        @Test
        @DisplayName("Should apply sorting when sortBy is provided")
        void getListCars_WithSorting_ShouldApplySort() {
            // Arrange
            RequestGetListCarsDto request = RequestGetListCarsDto.builder()
                    .page(1)
                    .size(10)
                    .sortBy("brand,asc")
                    .build();

            Page<CarsEntity> emptyPage = new PageImpl<>(Collections.emptyList());
            given(carsRepository.findListCars(any(), any(Pageable.class))).willReturn(emptyPage);

            // Act & Assert
            try (MockedStatic<SortConstant> mockedStatic = mockStatic(SortConstant.class)) {
                // Mock static field
                mockedStatic.when(SortConstant::getManagementCarSortFields)
                        .thenReturn(List.of("brand", "model", "year", "pricePerHour"));

                // Option: use reflection to set the static field if needed
//                ReflectionTestUtils.setField(SortConstant.class, "SORT_FIELDS_MANAGEMENT_CAR",
//                        Set.of("brand", "model", "year", "pricePerHour"));

                ResultPaginationDto<List<CarResponseDto>> result = managementCarsService.getListCars(request);
                assertThat(result).isNotNull();
            }
        }

        @ParameterizedTest
        @DisplayName("Should throw exception for invalid sort field")
        @MethodSource("com.duydev.backend.application.service.ManagementCarsServiceTest#invalidSortFieldsProvider")
        void getListCars_WithInvalidSortField_ShouldThrowException(String invalidSortBy) {
            // Arrange
            RequestGetListCarsDto request = RequestGetListCarsDto.builder()
                    .page(1)
                    .size(10)
                    .sortBy(invalidSortBy)
                    .build();

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.getListCars(request))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.SORT_BY_INVALID);
        }

        @Test
        @DisplayName("Should return empty list when no cars found")
        void getListCars_WhenNoCarsExist_ShouldReturnEmptyList() {
            // Arrange
            RequestGetListCarsDto request = RequestGetListCarsDto.builder()
                    .page(1)
                    .size(10)
                    .build();

            Page<CarsEntity> emptyPage = new PageImpl<>(Collections.emptyList());
            given(carsRepository.findListCars(any(), any(Pageable.class))).willReturn(emptyPage);

            // Act
            ResultPaginationDto<List<CarResponseDto>> result = managementCarsService.getListCars(request);

            // Assert
            assertThat(result.getData()).isEmpty();
            assertThat(result.getPagination().getTotalElements()).isZero();
        }
    }

    // ==================== GET INFORMATION CAR TESTS ====================

    @Nested
    @DisplayName("getInformationCar() Tests")
    class GetInformationCarTests {

        @Test
        @DisplayName("Should return car information with reviews successfully")
        void getInformationCar_WithReviews_ShouldReturnCompleteInfo() {
            // Arrange
            User customer = User.builder()
                    .username("customer1")
                    .build();
            customer.setId(2L);

            ReviewsEntity review = ReviewsEntity.builder()
                    .rating(5.0)
                    .comment("Excellent car!")
                    .build();
            review.setId(1L);

            BookingEntity booking = BookingEntity.builder()
                    .customer(customer)
                    .review(review)
                    .build();
            booking.setId(1L);

            testCar.setBookings(List.of(booking));

            given(carsRepository.findOneCar(1L)).willReturn(testCar);

            // Act
            ResponseDto<InformationCarResponseDto> response = managementCarsService.getInformationCar(1L);

            // Assert
            assertThat(response.getStatus()).isEqualTo(200);

            InformationCarResponseDto carInfo = response.getData();
            assertThat(carInfo.getBrand()).isEqualTo("Toyota");
            assertThat(carInfo.getUsername()).isEqualTo("testOwner");
            assertThat(carInfo.getLocation().getProvince()).isEqualTo("Ho Chi Minh");
            assertThat(carInfo.getReviews()).hasSize(1);
            assertThat(carInfo.getReviews().get(0).getRating()).isEqualTo(5);
        }

        @Test
        @DisplayName("Should return car information without reviews when bookings have no reviews")
        void getInformationCar_WithoutReviews_ShouldReturnEmptyReviewsList() {
            // Arrange
            BookingEntity bookingWithoutReview = BookingEntity.builder()
                    .review(null)
                    .build();
            bookingWithoutReview.setId(1L);

            testCar.setBookings(List.of(bookingWithoutReview));

            given(carsRepository.findOneCar(1L)).willReturn(testCar);

            // Act
            ResponseDto<InformationCarResponseDto> response = managementCarsService.getInformationCar(1L);

            // Assert
            assertThat(response.getData().getReviews()).isEmpty();
        }

        @Test
        @DisplayName("Should handle car with null location gracefully")
        void getInformationCar_WithNullLocation_ShouldReturnNullLocationFields() {
            // Arrange
            testCar.setLocation(null);
            given(carsRepository.findOneCar(1L)).willReturn(testCar);

            // Act
            ResponseDto<InformationCarResponseDto> response = managementCarsService.getInformationCar(1L);

            // Assert
            assertThat(response.getData().getLocation().getName()).isNull();
            assertThat(response.getData().getLocation().getProvince()).isNull();
        }

        @Test
        @DisplayName("Should throw AppException when car not found")
        void getInformationCar_WhenCarNotFound_ShouldThrowAppException() {
            // Arrange
            given(carsRepository.findOneCar(anyLong())).willReturn(null);

            // Act & Assert
            assertThatThrownBy(() -> managementCarsService.getInformationCar(999L))
                    .isInstanceOf(AppException.class)
                    .extracting("enumException")
                    .isEqualTo(EnumException.CAR_NOT_FOUND);
        }
    }

    // ==================== HELPER METHODS FOR PARAMETERIZED TESTS ====================

    static Stream<Arguments> invalidSortFieldsProvider() {
        return Stream.of(
                Arguments.of("invalidField,asc"),
                Arguments.of("hackerField,desc"),
                Arguments.of("password,asc")
        );
    }
}
