package com.wcrs.employee.dto;

public record EmployeeResponseDTO(String firstName,
                                  String lastName,
                                  String jobTitle,
                                  String username,
                                  String email,
                                  String NIN,
                                  PhoneDTO phone,
                                  String gender,
                                  int age) {
}
