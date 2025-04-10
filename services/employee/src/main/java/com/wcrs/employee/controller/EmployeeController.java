package com.wcrs.employee.controller;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO){
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequestDTO));
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<EmployeeResponseDTO>> allEmployees(){
        return ResponseEntity.ok(employeeService.viewAllEmployees());
    }

    @GetMapping("/view/{nin}")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@PathVariable("nin") String employeeId){
        return ResponseEntity.ok(employeeService.viewEmployee(employeeId));
    }

}
