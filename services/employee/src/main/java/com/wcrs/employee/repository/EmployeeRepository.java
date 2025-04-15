package com.wcrs.employee.repository;

import com.wcrs.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByUserName(String userName);

    boolean existsByNIN(String nin);

    Optional<Employee> findEmployeeByNIN(String nin);

    Void deleteByNIN(String nin);
}
