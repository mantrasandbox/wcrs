package com.wcrs.employee.exception;

public class NonExistentNINException extends RuntimeException {
    public NonExistentNINException(String message) {
        super(message);
    }
}


