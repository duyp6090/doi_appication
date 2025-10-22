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

    // Define enum exception for login social
    OAUTH2_CLIENT_NOT_FOUND(404, "OAuth2 client not found"),

    // Define enum exception for authentication/authorization
    TOKEN_IN_VALID(400, "Token is invalid"),
    TOKEN_NOT_FOUND(404, "Token not found"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),

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
    CAR_ID_NOT_NULL(400, "Car ID must not be null"),
    CAR_NOT_FOUND(404, "Car not found"),
    UPDATE_CAR_ERROR(500, "Update car error"),
    CAR_HAS_FUTURE_BOOKING(400, "Car has future booking"),

    // Define enum exception for rent cars
    START_TIME_NOT_NULL(400, "Start time must not be null"),
    END_TIME_NOT_NULL(400, "End time must not be null"),
    LIMIT_MIN_PAGE_1(400, "Limit min page is 1"),
    LIMIT_MIN_SIZE_1(400, "Limit min size is 1"),
    SORT_BY_INVALID(400, "Sort by is invalid"),
    CUSTOMER_ID_NOT_NULL(400, "Customer ID must not be null"),
    TOTAL_PRICE_NOT_NULL(400, "Total price must not be null"),
    BOOKING_NOT_FOUND(404, "Booking not found"),
    USER_HAS_NOT_CAR(400, "User has not booking this car"),
    CAN_NOT_CANCEL_BOOKING(400, "Can not cancel booking"),
    OWNER_HAS_NOT_CAR(400, "Owner has not car"),
    CAN_NOT_CONFIRM_BOOKING(400, "Can not confirm booking"),

    // Denfine enum exception for management Roles & Accounts
    INVALID_ROLE(400, "Invalid role"),

    // Define enum exception for managementing Account
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
    PHONE_NUMBER_INVALID(400, "Phone number is invalid"),
    EMAIL_INVALID(400, "Email is invalid"),
    INVALID_USERNAME_PASSWORD(401, "Invalid username or password");

    private int statusCode;
    private String message;

    EnumException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
