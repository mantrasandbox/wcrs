package com.wcrs.employee;

import com.github.javafaker.Faker;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AbstractTestContainersUnitTest {

    protected static final Faker FAKER = new Faker();

    @Container
    protected static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("employee-dao-unit-test")
            .withUsername("wcrs")
            .withPassword("wcrs");

    @DynamicPropertySource
    private static void configurePostgreSQL(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

    }
}


