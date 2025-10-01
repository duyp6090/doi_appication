package com.duydev.backend.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.duydev.backend.application.service.interfaceservice.IManagementCarsService;
import com.duydev.backend.domain.model.CarsEntity;
import com.duydev.backend.domain.repositories.CarsRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestCreateCarDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.util.CloudinaryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagementCarsService implements IManagementCarsService {
    private final CarsRepository carsRepository;
    private final CloudinaryUtil cloudinaryUtil;

    @Override
    public ResponseDto<String> createCar(RequestCreateCarDto requestCreateCarDto) {
        try {
            // Step By Step
            // 1. Create options to upload file
            Map<String, Object> options = Map.of(
                "upload_preset", "save-img",
                "quality", "auto",
                "fetch_format", "auto"
            );

            // 2. Upload file to cloudinary
            List<Map<String, Object>> results = cloudinaryUtil.bulkUpload(requestCreateCarDto.getImages(), options);

            // 3. Create Json string to save public_id and url for each image
            Map<String, String> images = new HashMap<>();
            for(Map<String, Object> imageInfo : results){
                String publicId = (String) imageInfo.get("public_id");
                String url = (String) imageInfo.get("secure_url");

                images.put(publicId, url);
            }

            String imagesJson =  new ObjectMapper().writeValueAsString(images);

            // 4. Create car entity and save
            CarsEntity car = CarsEntity.builder()
                .ownerId(requestCreateCarDto.getOwnerId())
                .brand(requestCreateCarDto.getBrand())
                .model(requestCreateCarDto.getModel())
                .licensePlate(requestCreateCarDto.getLicensePlate())
                .year(requestCreateCarDto.getYear())
                .pricePerHour(requestCreateCarDto.getPricePerHour())
                .locationId(requestCreateCarDto.getLocationId())
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
