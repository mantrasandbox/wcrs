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

    private static final Map<String, String> responses = new HashMap<>();

    @ExceptionHandler(DuplicateUserNameException.class)
    ResponseEntity<EmployeeExceptionResponse> handleDuplicateUserNameException(DuplicateUserNameException ex) {
        responses.put("duplicate_user_name", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new EmployeeExceptionResponse(responses));
    }
    @ExceptionHandler(DuplicateNinException.class)
    ResponseEntity<EmployeeExceptionResponse> handleDuplicateNinException(DuplicateNinException ex) {
        responses.put("duplicate_nin", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new EmployeeExceptionResponse(responses));
    }

    @ExceptionHandler(NonExistentNINException.class)
    ResponseEntity<EmployeeExceptionResponse> handleNonExistentNINException(NonExistentNINException ex) {
        responses.put("non_existent_nin", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new EmployeeExceptionResponse(responses));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
   @ExceptionHandler(RequestValidationException.class)
    ResponseEntity<EmployeeExceptionResponse> handleRequestValidationException(RequestValidationException ex) {
        responses.put("request_validation", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new EmployeeExceptionResponse(responses));
   }
}
