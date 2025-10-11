package com.duydev.backend.exception;

import lombok.Getter;

@Getter
public enum EnumException {
    // Define common enum exceptions
    SUCCESS(200, "Success"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_ERROR(500, "Internal server error"),
    BAD_REQUEST(400, "Bad request"),

    // Define enum exception for file upload/download
    FILE_NOT_FOUND(404, "File not found"),
    DELETE_FILE_ERROR(500, "Delete file error"),
    UPLOAD_FILE_ERROR(500, "Upload file error"),

    // Define enum exception for send notification such as email, sms
    SEND_SMS_FAIL(500, "Send SMS fail"),
    SEND_EMAIL_FAIL(500, "Send email fail"),

    // Define enum exception for authentication/authorization
    TOKEN_IN_VALID(400, "Token is invalid"),
    TOKEN_NOT_FOUND(404, "Token not found"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),

    // Define enum exception for login social
    OAUTH2_CLIENT_NOT_FOUND(404, "OAuth2 client not found"),

    // Define enum exception for management cars
    OWNER_ID_NOT_NULL(400, "Owner ID must not be null"),
    BRAND_NOT_NULL(400, "Brand must not be null"),
    MODEL_NOT_NULL(400, "Model must not be null"),
    LICENSE_PLATE_NOT_NULL(400, "License plate must not be null"),
    YEAR_NOT_NULL(400, "Year must not be null"),
    PRICE_PER_HOUR_NOT_NULL(400, "Price per hour must not be null"),
    LOCATION_ID_NOT_NULL(400, "Location ID must not be null"),
    LOCATION_NOT_NULL(400, "Location must not be null"),
    IMAGES_NOT_NULL(400, "Images must not be null"),
    IMAGES_NOT_EMPTY(400, "Images must not be empty"),
    CREATE_CAR_ERROR(500, "Create car error"),
    NAME_ADDRESS_NOT_NULL(400, "Name address must not be null"),
    NAME_ADDRESS_NOT_BLANK(400, "Name address must not be blank"),
    PROVINCE_NOT_NULL(400, "Province must not be null"),
    PROVINCE_NOT_BLANK(400, "Province must not be blank"),
    WARD_NOT_NULL(400, "Ward must not be null"),
    WARD_NOT_BLANK(400, "Ward must not be blank"),

    // Denfine enum exception for management roles
    INVALID_ROLE(400, "Invalid role"),

    // Define enum exception for managementing accounts
    USER_ID_NOT_NULL(400, "User ID must not be null"),
    USER_NOT_FOUND(404, "User not found"),
    USERNAME_EXIST(400, "Username already exists"),
    USERNAME_NOT_NULL(400, "Username not null"),
    USERNAME_NOT_BLANK(400, "Username not blank"),
    INVALID_TYPE_USER(400, "Invalid type user"),
    INVALID_STATUS_USER(400, "Invalid status user"),
    OLDPASSWORD_NOT_BLANK(400, "Old password not blank"),
    NEWPASSWORD_NOT_BLANK(400, "New password not blank"),
    PASSWORD_NOT_NULL(400, "Password not null"),
    OLDPASSWORD_INVALID(400, "Invalid old password"),
    OLDPASSWORD_NOT_NULL(400, "Old password not null"),
    NEWPASSWORD_NOT_NULL(400, "New password not null"),
    EMAIL_NOT_NULL(400, "Email not null"),
    EMAIL_NOT_BLANK(400, "Email not blank"),
    OTP_NOT_NULL(400, "OTP not null"),
    OTP_NOT_BLANK(400, "OTP not blank"),
    OTP_EXPIRED(400, "OTP expired"),
    OTP_NOT_FOUND(404, "OTP not found"),
    USER_ALREADY_OWNER(400, "User is already an owner"),
    INVALID_USERNAME_PASSWORD(401, "Invalid username or password");

    private int statusCode;
    private String message;

    EnumException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
