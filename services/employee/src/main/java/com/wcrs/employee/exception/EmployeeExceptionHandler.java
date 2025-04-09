package com.wcrs.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(DuplicateUserNameException.class)
    ResponseEntity<EmployeeExceptionResponse> handleDuplicateUserNameException(DuplicateUserNameException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new EmployeeExceptionResponse(ex.getMessage()));
    }
    @ExceptionHandler(DuplicateNinException.class)
    ResponseEntity<EmployeeExceptionResponse> handleDuplicateNinException(DuplicateNinException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new EmployeeExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

}}
