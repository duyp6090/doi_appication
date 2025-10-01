package com.duydev.backend.util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "CloudinaryUtil")
public class CloudinaryUtil {

    private final Cloudinary cloudinary;

    private final CloudinaryAsyncUtil cloudinaryAsyncUtil;

    private static final String FOLDER_PATH = "assets/";

    public List<Map<String, Object>> bulkUpload(List<MultipartFile> files, Map<String, Object> options) {
        List<Map<String, Object>> results = new ArrayList<>();
        List<CompletableFuture<Map<String, Object>>> futures = new ArrayList<>();
        try {
            // Create folder assets if not exist then save image to folder assets in
            // resources
            Path folder = Path.of(FOLDER_PATH);
            if (!folder.toFile().exists()) {
                folder.toFile().mkdirs();
            }

            // From file that was saved into folder
            for (MultipartFile file : files) {
                CompletableFuture<Map<String, Object>> responseUpload = cloudinaryAsyncUtil.asyncUpload(file, options);
                futures.add(responseUpload);
            }

            // Wait for all uploads to complete
            CompletableFuture<Void> allResultUpload = CompletableFuture
                    .allOf(futures.toArray(new CompletableFuture[0]));
            allResultUpload.join();

            // Collect results
            for (CompletableFuture<Map<String, Object>> future : futures) {
                results.add(future.join());
            }
        } catch (Exception e) {
            log.error("Error when upload file to cloudinary: {}", e.getMessage());
            throw new AppException(EnumException.UPLOAD_FILE_ERROR);
        }

        return results;
    }

    public void deletionResource(List<String> publicIds, Map<String, Object> options) {
        try {
            cloudinary.api().deleteResources(publicIds, options);
        } catch (Exception e) {
            log.error("Error when delete resource from cloudinary: {}", e.getMessage());
            throw new AppException(EnumException.DELETE_FILE_ERROR);
        }
    }

    public List<Map<String, Object>> reBulkUpload(List<MultipartFile> files, List<String> publictIds) {
        List<Map<String, Object>> results = new ArrayList<>();
        List<CompletableFuture<Map<String, Object>>> futures = new ArrayList<>();
        try {
            // Delete old files from cloudinary
            for (int i = 0; i < publictIds.size(); i++) {
                String publicId = publictIds.get(i);
                MultipartFile file = files.get(i);
                CompletableFuture<Map<String, Object>> responseUpload = cloudinaryAsyncUtil.asyncUpload(file,
                        Map.of("public_id", publicId,
                                "overwrite", true));
                futures.add(responseUpload);

                // Wait for all uploads to complete
                CompletableFuture<Void> allResultsUpload = CompletableFuture
                        .allOf(futures.toArray(new CompletableFuture[0]));
                allResultsUpload.join();

                // Collect results
                for (CompletableFuture<Map<String, Object>> future : futures) {
                    results.add(future.join());
                }
            }
        } catch (Exception e) {
            log.error("Error when re-upload file to cloudinary: {}", e.getMessage());
            throw new AppException(EnumException.UPLOAD_FILE_ERROR);
        }
        return results;
    }
}
