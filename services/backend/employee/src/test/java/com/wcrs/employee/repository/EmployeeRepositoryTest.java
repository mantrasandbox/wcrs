package com.wcrs.employee.repository;

import com.wcrs.employee.AbstractTestContainersUnitTest;
import com.wcrs.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest extends AbstractTestContainersUnitTest {

    private EmployeeRepository underTest;

    @Test
    void itShouldExistsByUserName() {
        // Given
        Employee employee = new Employee();

        // When
        // Then
    }

    @Test
    void itShouldExistsByNIN() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldFindEmployeeByNIN() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldDeleteByNIN() {
        // Given
        // When
        // Then
    }
}