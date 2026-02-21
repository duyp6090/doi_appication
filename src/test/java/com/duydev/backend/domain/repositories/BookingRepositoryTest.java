package com.duydev.backend.domain.repositories;

import com.duydev.backend.domain.enums.StatusBooking;
import com.duydev.backend.domain.model.BookingEntity;
import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.domain.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("BookingRepository Test")
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        // Prepare test data for testing
        // 1.1 Create User Entity
        User user = User.builder()
                .username("Test User")
                .email("duyp6090@gmail.com").build();

        entityManager.persist(user);

        // 1.2 Create Car Entity
        CarsEntity car = CarsEntity.builder()
                .brand("Toyota")
                .model("Camry")
                .year(2020)
                .build();
        entityManager.persist(car);

        // 1.3 Create Booking Entity
        BookingEntity bookingEntity = BookingEntity.builder()
                .customer(user)
                .car(car)
                .startTime(new Date())
                .endTime(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                .status(StatusBooking.CONFIRMED)
                .build();
        entityManager.persist(bookingEntity);
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
    }

    private static Stream<Arguments> existsByCarIdAndEndTimeAfterAndStatusProvider() {
        return Stream.of(
                Arguments.of(1L, new Date(), StatusBooking.PENDING, false),
                Arguments.of(1L, new Date(System.currentTimeMillis() + 60 * 60 * 1000), StatusBooking.CONFIRMED, true)
        );
    }

    @ParameterizedTest
    @MethodSource("existsByCarIdAndEndTimeAfterAndStatusProvider")
    @DisplayName("Test_01 - existsByCarIdAndEndTimeAfterAndStatus")
    void existsByCarIdAndEndTimeAfterAndStatus(
            Long carId,
            Date time,
            StatusBooking status,
            boolean expectedResult
    ) {
        // 1. Calling method
        boolean actualResult = bookingRepository.existsByCarIdAndEndTimeAfterAndStatus(carId, time, status);

        // 2. Verify the result
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void findByIdForUpdate() {
    }

    @Test
    void findBookingsOverlap() {
    }

    @Test
    void findByCustomer_IdAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual() {
    }

    @Test
    void findById() {
    }
}