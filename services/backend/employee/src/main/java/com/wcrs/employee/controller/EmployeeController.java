package com.wcrs.employee.controller;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.dto.EmployeeUpdateDTO;
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
    public ResponseEntity<List<EmployeeResponseDTO>> viewAllEmployees(
            @RequestParam(name="page",defaultValue="0",required = false ) int page,
            @RequestParam(name="size",defaultValue="10",required = false ) int size
    ){
        return ResponseEntity.ok(employeeService.viewAllEmployees(page,size));
    }

    @GetMapping("/view/{nin}")
    public ResponseEntity<EmployeeResponseDTO> viewEmployee(@PathVariable("nin") String nin){
        return ResponseEntity.ok(employeeService.viewEmployee(nin));
    }

    @PutMapping("/update/{nin}")
    public ResponseEntity<Integer> updateEmployee(@Valid @RequestBody EmployeeUpdateDTO employeeUpdateDTO, @PathVariable("nin") String nin){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeUpdateDTO, nin));

    }

    @DeleteMapping("/delete/{nin}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("nin") String nin){
        return ResponseEntity.ok(employeeService.removeEmployee(nin));
    }


}
