package com.wcrs.inventory.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record MaterialRequestDTO (

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Unit of Measure is required")
        String unitOfMeasure,

        @Digits(integer = 10, fraction = 4, message = "Cost per Unit should be a valid number")
        BigDecimal costPerUnit,

        @PositiveOrZero(message = "Cost per Unit should be positive or zero")
        Double quantity,

        @Digits(integer = 10, fraction = 4, message = "Total Cost should be a valid number")
        BigDecimal totalCost
){}

