package com.duydev.backend.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

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

    private static final String FOLDER_PATH = "assets/";

    public Map<String, Object> bulkUpload(List<MultipartFile> files, Map<String, Object> options){
        Map<String, Object> response = null;
        try {
            // Create folder assets if not exist then save image to folder assets in resources
            Path folder = Path.of(FOLDER_PATH);
            if (!folder.toFile().exists()) {
                folder.toFile().mkdirs();
            }
            
            // From file that was saved into folder
            for(MultipartFile file : files){
                String nameFile =  file.getOriginalFilename();

                // Define target path to save file
                Path targetPath = folder.resolve(nameFile);

                file.transferTo(targetPath);

                // Upload file to cloudinary
                response = cloudinary.uploader().upload(file, options);
                Files.delete(targetPath);
            }
        } catch (Exception e) {
            throw new AppException(EnumException.UPLOAD_FILE_ERROR);
        }

        return response;
    }
}
