package com.wcrs.inventory.dto;

import jakarta.validation.constraints.NotBlank;

public record MaterialResponseDTO (
        String name,

        String description,

        String unitOfMeasure,

        Double costPerUnit,

        Double quantity,

        Double totalCost
){}
