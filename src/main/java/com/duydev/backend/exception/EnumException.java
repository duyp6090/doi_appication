package com.duydev.backend.exception;

import lombok.Getter;

@Getter
public enum EnumException {
    // Define common enum exceptions
    SUCCESS(200, "Success"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_ERROR(500, "Internal server error"),
    BAD_REQUEST(400, "Bad request"),

    // Cloudinary service exceptions
    DELETE_FILE_ERROR(500, "Delete file error"),
    UPLOAD_FILE_ERROR(500, "Upload file error"),

    // Define enum exception for authentication/authorization
    TOKEN_IN_VALID(400, "Token is invalid"),
    TOKEN_NOT_FOUND(404, "Token not found"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),

    // Define enum exception for login social
    OAUTH2_CLIENT_NOT_FOUND(404, "OAuth2 client not found"),

    // Denie enum exception for management cars
    OWNER_ID_NOT_NULL(400, "Owner ID must not be null"),
    BRAND_NOT_NULL(400, "Brand must not be null"),
    MODEL_NOT_NULL(400, "Model must not be null"),
    LICENSE_PLATE_NOT_NULL(400, "License plate must not be null"),
    YEAR_NOT_NULL(400, "Year must not be null"),
    PRICE_PER_HOUR_NOT_NULL(400, "Price per hour must not be null"),
    LOCATION_ID_NOT_NULL(400, "Location ID must not be null"),
    IMAGES_NOT_NULL(400, "Images must not be null"),
    IMAGES_NOT_EMPTY(400, "Images must not be empty"),
    CREATE_CAR_ERROR(500, "Create car error"),

    // Define enum exception for user
    USERNAME_EXIST(400, "Username already exists"),
    INVALID_USERNAME_PASSWORD(401, "Invalid username or password");

    private int statusCode;
    private String message;

    EnumException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
