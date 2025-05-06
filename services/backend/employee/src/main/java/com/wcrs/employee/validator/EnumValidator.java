package com.wcrs.employee.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum annotation) {
        this.enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        // Allow null (use @NotNull if needed separately)
        if (value == null) return true;

        Enum<?>[] constants = enumClass.getEnumConstants();
        for (Enum<?> constant : constants) {
            if (constant.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
