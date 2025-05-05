package com.wcrs.employee.service;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.dto.EmployeeUpdateDTO;
import com.wcrs.employee.exception.DuplicateNinException;
import com.wcrs.employee.exception.DuplicateUserNameException;
import com.wcrs.employee.exception.NonExistentNINException;
import com.wcrs.employee.exception.RequestValidationException;
import com.wcrs.employee.mapper.EmployeeMapper;
import com.wcrs.employee.model.Employee;
import com.wcrs.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


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


    public List<EmployeeResponseDTO>  viewAllEmployees(int page, int size) {

        Pageable pageable= PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Employee> employees = employeeRepository.findAll(pageable);

        return employees.stream()
                .map(employeeMapper::toEmployeeResponseDTO)
                .toList();
    }

    public EmployeeResponseDTO viewEmployee(String NIN) {

        // check if queried employee exists
        Employee existEmployee = employeeRepository.findEmployeeByNIN(NIN)
                .orElseThrow(() -> new NonExistentNINException("This employee does not exist"));
        return employeeMapper.toEmployeeResponseDTO(existEmployee);
       }


    public Integer updateEmployee(@Valid EmployeeUpdateDTO employeeUpdateDTO, String nin) {
        // check if employee for update exists
        Employee existEmployee = employeeRepository.findEmployeeByNIN(nin)
                .orElseThrow(() -> new NonExistentNINException("This NIN does not exist"));

       boolean changesAvailable = false;

        // check for what field is to be updated
        if (employeeUpdateDTO.firstName() != null && !employeeUpdateDTO.firstName().equals(existEmployee.getFirstName())) {
            existEmployee.setFirstName(employeeUpdateDTO.firstName());
            changesAvailable = true;
        }

        if (employeeUpdateDTO.lastName() != null && !employeeUpdateDTO.lastName().equals(existEmployee.getLastName())) {
            existEmployee.setLastName(employeeUpdateDTO.lastName());
            changesAvailable = true;
        }

        if (employeeUpdateDTO.dateOfBirth() != null && !employeeUpdateDTO.dateOfBirth().equals(existEmployee.getDateOfBirth())) {
            existEmployee.setDateOfBirth(employeeUpdateDTO.dateOfBirth());
            changesAvailable = true;
        }

        if (employeeUpdateDTO.NIN() != null && !employeeUpdateDTO.NIN().equals(existEmployee.getNIN())) {
            existEmployee.setNIN(employeeUpdateDTO.NIN());
            changesAvailable = true;
        }

        if (employeeUpdateDTO.userName() != null && !employeeUpdateDTO.userName().equals(existEmployee.getUserName())) {
            existEmployee.setUserName(employeeUpdateDTO.userName());
            changesAvailable = true;
        }

        if(!changesAvailable){
            throw new RequestValidationException("No changes found");
        }
        return employeeRepository.saveAndFlush(existEmployee).getId();


    }

    public Void removeEmployee(String nin) {
        // check whether nin exist
        if(!employeeRepository.existsByNIN(nin)){
            throw new NonExistentNINException("This NIN does not exist");
        }
        return employeeRepository.deleteByNIN(nin);


    }
}
