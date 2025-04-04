package com.wcrs.employee.repository;

import com.wcrs.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByUserName(String userName);

    boolean existsByNIN(String nin);
}
