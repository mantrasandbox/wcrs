package com.wcrs.inventory.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {
    private static final Map<String, String> responses = new HashMap<>();

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<InventoryExceptionResponse> handleSupplierNotFoundException(SupplierNotFoundException ex) {
        responses.put("Non-existent email", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new InventoryExceptionResponse(responses));
    }

}
