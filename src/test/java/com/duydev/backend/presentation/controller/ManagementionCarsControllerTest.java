package com.duydev.backend.presentation.controller;

import com.duydev.backend.application.service.CustomeUserDetailsService;
import com.duydev.backend.application.service.interfaceservice.IManagementCarsService;
import com.duydev.backend.config.AppConfig;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.controller.ManagementionCarsController;
import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.request.RequestLocationDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.util.JwtUtil;
import com.duydev.backend.utils.anotation.IFunctional.WithMockCustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManagementionCarsController.class)
@Import(AppConfig.class)
class ManagementionCarsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    IManagementCarsService managementCarsService;

    @MockitoBean
    JwtUtil jwtUtil;

    @MockitoBean
    private CustomeUserDetailsService customeUserDetailsService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // prepare data
    private static @NonNull RequestCreateCarDto getRequestCreateCarDto() {
        RequestCreateCarDto carDto = new RequestCreateCarDto();
        carDto.setOwnerId(1L);
        carDto.setBrand("Toyota");
        carDto.setModel("Camry");
        carDto.setLicensePlate("ABC-1234");
        carDto.setYear(2020);
        carDto.setPricePerHour(new BigDecimal(100000)); // BigDecimal value
        // Create RequestLocationDto
        RequestLocationDto requestLocationDto = new RequestLocationDto();
        requestLocationDto.setName("phu thien");
        requestLocationDto.setProvince("binh duong");
        requestLocationDto.setWard("thuan an");
        requestLocationDto.setLatitude(10.123456);
        requestLocationDto.setLongitude(106.123456);
        carDto.setLocation(requestLocationDto);
        return carDto;
    }

    private static @NonNull MockMultipartFile getMockMultipartFileCar(String name, String originalFilename, String contentFile, String mediaType) throws Exception {
        return new MockMultipartFile(
                name,
                originalFilename,
                mediaType,
                contentFile.getBytes()
        );
    }

    @Test
    @WithMockCustomUser(
            userId = 99L,
            username = "Duy_Dev",
            roles = {"OWNER"},
            permissions = {"create"}
    )
    void createCarSuccess() throws Exception {
        // 1. Mock data
        // 1.1 Create RequestCreateCarDto
        RequestCreateCarDto carDto = getRequestCreateCarDto();

        MockMultipartFile mockCar = new MockMultipartFile(
                "car",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(carDto)
        );


        // 1.2 Mock MultipartFile
        MockMultipartFile image1 = getMockMultipartFileCar(
                "images",
                "car_image_abc.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "Dummy Image Content abc"
        );

        // 1.3 Create response object
        ResponseDto<String> responseDto = ResponseDto.<String>builder()
                .status(HttpStatus.OK.value())
                .message(List.of("Create car successfully"))
                .build();

        // 2. Mock service layer
        when(managementCarsService.createCar(any(RequestCreateCarDto.class), anyList()))
                .thenReturn(responseDto); // You can customize the return value as needed

        // 3 . Perform request and verify response
        mockMvc.perform(
                        multipart("/api/v1/managemention-cars/create")
                                .file(mockCar)
                                .file(image1)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", containsInAnyOrder(responseDto.getMessage().toArray())));

        // 4 Verify
        verify(managementCarsService, times(1)).createCar(
                any(RequestCreateCarDto.class),
                anyList()
        );
    }

    @Test
    @WithMockCustomUser(
            userId = 99L,
            username = "duy_len",
            roles = {"CUSTOMER"},
            permissions = {"create"}
    )
    void createCarFail() throws Exception {
        // 1. Prepare data

        // 1.1 CarDto and images
        RequestCreateCarDto carDto = getRequestCreateCarDto();
        MockMultipartFile mockCar = new MockMultipartFile(
                "car",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(carDto)
        );

        MockMultipartFile image1 = getMockMultipartFileCar(
                "images",
                "car_image_1.jpg",
                MediaType.IMAGE_PNG_VALUE,
                "Dummy Image Content 1"
        );

        // 1.2 Response
        ResponseDto<String> responseDto = ResponseDto.<String>builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(List.of(EnumException.FORBIDDEN.getMessage()))
                .build();

        // 2. Mock for service layer
        when(managementCarsService.createCar(any(RequestCreateCarDto.class), anyList()))
                .thenReturn(responseDto);

        // 3. Create request and verify response
        mockMvc.perform(
                        multipart("/api/v1/managemention-cars/create")
                                .file(image1)
                                .file(mockCar)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                ).andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message", containsInAnyOrder(responseDto.getMessage().toArray())));

        verify(managementCarsService, times(0)).createCar(
                any(RequestCreateCarDto.class),
                anyList()
        );

    }

    @Test
    void updateCar() {

    }

    @Test
    void deleteCar() {
    }

    @Test
    void getListCars() {
    }

    @Test
    void getInformationCar() {
    }
}