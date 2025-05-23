package com.wcrs.inventory.exception;

import lombok.Builder;

import java.util.Map;

@Builder
public record InventoryExceptionResponse(
        Map<String, String> message
) {
}
