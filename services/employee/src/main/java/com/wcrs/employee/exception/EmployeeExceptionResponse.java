package com.wcrs.employee.exception;

import lombok.Builder;

@Builder
public record EmployeeExceptionResponse(String message) {
}
