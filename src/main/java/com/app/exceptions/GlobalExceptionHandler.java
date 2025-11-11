package com.app.exceptions;

import com.app.models.responses.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex, WebRequest request) {
        log.error("Exception: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(ExceptionResponse
                .builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("UNEXPECTED ERROR")
                .details("An unexpected error occurred. Please try again later.")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.warn("ResourceNotFoundException: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(ExceptionResponse
                .builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message("RESOURCE NOT FOUND")
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException ex, WebRequest request) {
        log.warn("ResourceAlreadyExistException: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(ExceptionResponse
                .builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message("RESOURCE ALREADY EXIST")
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("MethodArgumentNotValidException: {}", ex.getMessage(), ex);

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return new ResponseEntity<>(ExceptionResponse
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("VALIDATION ERROR")
                .details(fieldErrors)
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UpdatePasswordValidationException.class)
    public ResponseEntity<ExceptionResponse> handleUpdatePasswordValidationException(UpdatePasswordValidationException ex, WebRequest request) {
        log.warn("UpdatePasswordValidationException: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(ExceptionResponse
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("PASSWORD VALIDATION ERROR")
                .details(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false))
                .build(), HttpStatus.BAD_REQUEST);
    }
}
