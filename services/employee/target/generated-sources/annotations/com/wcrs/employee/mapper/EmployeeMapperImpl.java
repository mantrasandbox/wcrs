package com.wcrs.employee.mapper;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.model.Employee;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-17T13:19:44+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEmployee(EmployeeRequestDTO employeeRequestDTO) {
        if ( employeeRequestDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setDateOfBirth( employeeRequestDTO.dateOfBirth() );
        employee.setFirstName( employeeRequestDTO.firstName() );
        employee.setLastName( employeeRequestDTO.lastName() );
        employee.setUserName( employeeRequestDTO.userName() );
        employee.setNIN( employeeRequestDTO.NIN() );

        return employee;
    }

    @Override
    public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        String nIN = null;

        nIN = employee.getNIN();

        String fullName = employee.getFullName();
        int age = employee.getAge();

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO( fullName, nIN, age );

        return employeeResponseDTO;
    }

    @Override
    public EmployeeResponseDTO toEmployeeResponseDTO(Page employee) {
        if ( employee == null ) {
            return null;
        }

        String fullName = null;
        String nIN = null;
        int age = 0;

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO( fullName, nIN, age );

        return employeeResponseDTO;
    }
}
