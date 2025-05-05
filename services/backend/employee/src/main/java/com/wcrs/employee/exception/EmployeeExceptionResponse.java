package com.wcrs.employee.exception;

import lombok.Builder;

import java.util.Map;

@Builder
public record EmployeeExceptionResponse(Map<String, String> message) {
}
