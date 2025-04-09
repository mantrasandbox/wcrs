package com.wcrs.employee.controller;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO){
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequestDTO));

    }

}
