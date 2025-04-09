package com.wcrs.employee.service;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.exception.DuplicateNinException;
import com.wcrs.employee.exception.DuplicateUserNameException;
import com.wcrs.employee.mapper.EmployeeMapper;
import com.wcrs.employee.model.Employee;
import com.wcrs.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;


    public EmployeeResponseDTO createEmployee(@Valid EmployeeRequestDTO employeeRequestDTO) {

        // check if username and nin is taken
        String userName = employeeRequestDTO.userName();
        String nin = employeeRequestDTO.NIN();

        if (employeeRepository.existsByUserName(userName)) {
            throw new DuplicateUserNameException("This username exists");
        }

        if(employeeRepository.existsByNIN(nin)){
            throw new DuplicateNinException("This NIN exists");
        }

        Employee savedEmployee = employeeRepository.saveAndFlush(employeeMapper.toEmployee(employeeRequestDTO));

        log.info("Employee created: {} " , savedEmployee);

        // TODO: log postgresql activity


        return employeeMapper.toEmployeeResponseDTO(savedEmployee);
    }

}
