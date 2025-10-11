package com.duydev.backend.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.duydev.backend.application.service.interfaceservice.IManagementCarsService;
import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.domain.model.LocationEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.CarsRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.request.RequestLocationDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.util.CloudinaryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagementCarsService implements IManagementCarsService {
    private final CarsRepository carsRepository;

    private final UserRepository userRepository;

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

}
