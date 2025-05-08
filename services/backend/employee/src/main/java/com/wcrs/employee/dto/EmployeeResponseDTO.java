package com.wcrs.employee.dto;

import java.util.List;

public record EmployeeResponseDTO(String firstName,
                                  String lastName,
                                  String jobTitle,
                                  String username,
                                  String email,
                                  String NIN,
                                  List<PhoneDTO> phone,
                                  String gender,
                                  int age) {
}
