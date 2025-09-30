package com.duydev.backend.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
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
        Map<String, Object> results = new HashMap<>();
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
                Map<String, Object> response = cloudinary.uploader().upload(file, options);
                results.put(nameFile, response);

                // Delete image from folder assets
                Files.delete(targetPath);
            }
        } catch (Exception e) {
            throw new AppException(EnumException.UPLOAD_FILE_ERROR);
        }

        return results;
    }

    public void deletionResource(List<String> publicIds, Map<String, Object> options){
        try {
            cloudinary.api().deleteResources(publicIds, options); 
        } catch (Exception e) {
            log.error("Error when delete resource from cloudinary: {}", e.getMessage());
            throw new AppException(EnumException.DELETE_FILE_ERROR);
        }
    }

    public Map<String, Object> reBulkUpload(List<MultipartFile> files, List<String> publictIds){
        Map<String, Object> results = new HashMap<>();
        try {
            // Delete old files from cloudinary
            for(int i = 0; i < publictIds.size(); i++){
                String publicId = publictIds.get(i);
                MultipartFile file = files.get(i);
                Map<String, Object> uploadResult = cloudinary.uploader().upload(file, Map.of("public_id", publicId, "overwrite", true));
                results.put(file.getOriginalFilename(), uploadResult);
            }
        } catch (Exception e) {
            log.error("Error when re-upload file to cloudinary: {}", e.getMessage());
            throw new AppException(EnumException.UPLOAD_FILE_ERROR);
        }
        return results;
    }
}
