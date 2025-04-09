package com.wcrs.employee.mapper;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);

    @Mapping(target = "fullName", expression = "java(employee.getFullName())")
    @Mapping(target = "age", expression = "java(employee.getAge())")
    EmployeeResponseDTO toEmployeeResponseDTO(Employee employee);

}
