package com.example.javaservice09.advice_controller;

import com.example.javaservice09.model.dto.response.DataErrorResponse;
import org.apache.coyote.BadRequestException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataErrorResponse<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(new DataErrorResponse<>("Invalid input data", errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DataErrorResponse<Map<String, String>>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return new ResponseEntity<>(new DataErrorResponse<>("Constraint violation", errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DataErrorResponse<String>> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(new DataErrorResponse<>("Element not found", ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<DataErrorResponse<String>> handleDateTimeParseException(DateTimeParseException ex) {
        return new ResponseEntity<>(new DataErrorResponse<>("Invalid date format", ex.getParsedString(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<DataErrorResponse<String>> handleNoResourceFoundException(NoResourceFoundException ex) {
        return new ResponseEntity<>(new DataErrorResponse<>("Resource not found", ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DataErrorResponse<String>> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new DataErrorResponse<>("Bad request", ex.getMessage(),HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
