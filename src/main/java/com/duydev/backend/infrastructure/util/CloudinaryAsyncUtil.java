package com.duydev.backend.infrastructure.util;

import com.cloudinary.Cloudinary;
import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j(topic = "CloudinaryAsyncUtil")
@Component
@RequiredArgsConstructor
public class CloudinaryAsyncUtil {
    private final Cloudinary cloudinary;

    private static final String FOLDER_PATH = "assets/";

    @Async
    public CompletableFuture<Map<String, Object>> asyncUpload(MultipartFile file, Map<String, Object> options) {
        Map<String, Object> result;
        try {
            // Create folder assets if not exist then save image to folder assets in
            // resources
            Path folder = Path.of(FOLDER_PATH);
            if (!folder.toFile().exists()) {
                Files.createDirectories(folder);
            }

            // From file that was saved into folder
            String nameFile = Objects.requireNonNull(file.getOriginalFilename()).replaceAll("[^a-zA-Z0-9.\\-]", "_");

            // Define target path to save file
            Path targetPath = folder.resolve(nameFile);

            file.transferTo(targetPath);

            // Upload file to cloudinary
            result = cloudinary.uploader().upload(file.getBytes(), options);

            // Delete image from folder assets
            Files.delete(targetPath);

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            log.error("Error when upload file to cloudinary: {}", e.getMessage());
            throw new AppException(EnumException.UPLOAD_FILE_ERROR);
        }
    }
}
