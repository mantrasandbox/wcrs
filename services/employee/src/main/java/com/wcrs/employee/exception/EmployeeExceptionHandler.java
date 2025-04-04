package com.wcrs.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
