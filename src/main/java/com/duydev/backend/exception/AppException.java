package com.duydev.backend.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{
    private final EnumException enumException;

    public AppException(EnumException enumException) {
        super(enumException.getMessage());
        this.enumException = enumException;
    }

    public AppException(EnumException enumException, String message) {
        super(message);
        this.enumException = enumException;
    }
}
