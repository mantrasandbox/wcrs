package com.wcrs.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaterialRequestDTO (

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Unit of Measure is required")
        String unitOfMeasure,

        @NotBlank(message = "Cost per Unit is required")
        Double costPerUnit,

        @NotBlank(message = "Quantity is required")
        Double quantity,

        Double totalCost
){}
