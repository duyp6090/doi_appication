package com.duydev.backend.domain.repositories;

import com.duydev.backend.application.mapper.CarsResponseProjection;
import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.domain.model.LocationEntity;
import com.duydev.backend.domain.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("CarsRepository Test")
class CarsRepositoryTest {

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private TestEntityManager entityManager;

    // Common Mock Data
    private Date currentTime;
    private Date futureTime;

    @BeforeEach
    void setUp() {
        // Time
        OffsetDateTime now = OffsetDateTime.now();

        this.currentTime = Date.from(now.toInstant());

        this.futureTime = Date.from(now.plusHours(2).toInstant());

        // Data
        User owner = User.builder().username("DuyDev").email("duy@test.com").build();
        entityManager.persist(owner);

        LocationEntity loc = LocationEntity.builder()
                .name("Pleiku Center")
                .province("Gia Lai")
                .ward("Phường Diên Hồng")
                .longitude(108.0) // longitude
                .latitude(14.0)   // latitude
                .build();
        entityManager.persist(loc);

        CarsEntity car = CarsEntity.builder()
                .brand("Toyota")
                .model("Camry")
                .year(2022)
                .pricePerHour(new BigDecimal("150.0"))
                .location(loc)
                .user(owner)
                .build();
        entityManager.persist(car);

        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("Test findCars method")
    class FindCarsTests {
        @Test
        @DisplayName("Should return list of cars based on search criteria")
        void shouldReturnListOfCarsBasedOnSearchCriteria() {
            // arrange
            Pageable pageable = PageRequest.of(0, 10);

            // act
            Page<CarsResponseProjection> result = carsRepository.findCars(
                    "Toyota", 2022, "Gia Lai", "Diên Hồng",
                    100.0, 200.0, 108.0, 14.0, currentTime, futureTime, pageable
            );

            // assert
            assertThat(result.getContent()).isNotEmpty();
            assertThat(result.getNumber()).isEqualTo(0);
        }
    }

    @Test
    void findCars() {
    }

    @Test
    void validBookingTime() {
    }

    @Test
    void findListCars() {
    }

    @Test
    void findOneCar() {
    }

    @Test
    void findCarsAndGetBookingsByUserId() {
    }
}