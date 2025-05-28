package com.wcrs.inventory.exception;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(String message)  {
        super(message);
    }
}
