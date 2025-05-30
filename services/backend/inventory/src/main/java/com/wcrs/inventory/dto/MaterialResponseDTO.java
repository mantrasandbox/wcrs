package com.wcrs.inventory.dto;

import java.math.BigDecimal;

public record MaterialResponseDTO (
        String name,

        String description,

        String unitOfMeasure,

        BigDecimal costPerUnit,

        Double quantity,

        BigDecimal totalCost
){}
