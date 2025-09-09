package com.duydev.backend.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.duydev.backend.presentation.dto.response.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseDto<Object>> handleRuntimeException(RuntimeException e) {
        // init ResponseDto
        ResponseDto<Object> restResponse = new ResponseDto<>();

        // Set parameters
        restResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        restResponse.setError("RuntimeException");
        restResponse.setMessage(List.of(e.getMessage()));
        restResponse.setData(null);

        // Return response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ResponseDto<Object>> handleAppException(AppException e) {
        // Get errorCode
        EnumException errorCode = e.getEnumException();

        // init ResponseDto
        ResponseDto<Object> restResponse = new ResponseDto<>();

        // Set parameters
        restResponse.setStatus(errorCode.getStatusCode());
        restResponse.setError("AppException");
        restResponse.setMessage(List.of(e.getMessage()));
        restResponse.setData(null);

        // Return response
        return ResponseEntity.status(errorCode.getStatusCode()).body(restResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        
        // Init ResponseDto
        ResponseDto<Object> restResponse = new ResponseDto<>();

        // Define Enumexception default
        EnumException errorCode = EnumException.BAD_REQUEST;

        // Create map to save errors and name of EnumException
        Map<String, String> errors = new HashMap<>();
        try {
            e.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            // Create list error to save message
            List<String> messages = errors.entrySet().stream().map(
                error -> {
                    String value = error.getValue();
                    EnumException enumException = EnumException.valueOf(value);
                    return enumException.getMessage();
                }
            ).toList();

            restResponse.setMessage(messages);
        } catch (Exception ex) {
            restResponse.setMessage(List.of(errorCode.getMessage()));
        }

        // Set parameters
        restResponse.setStatus(errorCode.getStatusCode());
        restResponse.setError("Bad Field Request");
        restResponse.setData(null);

        // Return response
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ResponseDto<Object>> handleAuthenticationException(
        AuthenticationException e
    ) {
        // Innit responseDto
        EnumException errorCode = EnumException.UNAUTHORIZED;
        ResponseDto<Object> restResponse = ResponseDto.builder()
            .status(errorCode.getStatusCode())
            .error("Unauthorized")
            .message(List.of(errorCode.getMessage()))
            .data(null)
            .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(restResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ResponseDto<Object>> handleAccessDeniedException(
        AccessDeniedException e
    ) {
        // Innit responseDto
        EnumException errorCode = EnumException.FORBIDDEN;
        ResponseDto<Object> restResponse = ResponseDto.builder()
            .status(errorCode.getStatusCode())
            .error("Forbidden")
            .message(List.of(errorCode.getMessage()))
            .data(null)
            .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(restResponse);
    }

}