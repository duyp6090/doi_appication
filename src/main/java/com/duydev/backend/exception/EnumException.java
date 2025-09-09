package com.duydev.backend.exception;

import lombok.Getter;

@Getter
public enum EnumException {
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_ERROR(500, "Internal server error"),
    INVALID_INPUT(400, "Invalid input"),
    INVALID_USERNAME_PASSWORD(401, "Invalid username or password"),
    SUCCESS(200, "Success"),
    TOKEN_IN_VALID(400, "Token is invalid"),
    TOKEN_NOT_FOUND(400, "Token not found"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    BAD_REQUEST(400, "Bad request");

    private int statusCode;
    private String message;

    EnumException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
