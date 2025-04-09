package com.wcrs.employee.dto;


import jakarta.validation.constraints.*;


import java.time.LocalDate;

public record EmployeeRequestDTO(

        @Past(message = "The date of birth must be before the present date")
        LocalDate dateOfBirth,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Username must be unique")
        String userName,

        @Size(max = 14,message = "Valid nin is required")
        @Pattern(regexp = "^[A-Za-z0-9]{14}$", message = "Nin must be alpha numeric and 14 characters long")
        String NIN
) {

}
