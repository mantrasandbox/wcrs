package com.wcrs.employee;

import com.github.javafaker.Faker;
import com.wcrs.employee.config.KafkaProducer;
import com.wcrs.employee.enums.EventType;
import com.wcrs.employee.enums.Gender;
import com.wcrs.employee.enums.PhoneCategory;
import com.wcrs.employee.model.Employee;
import com.wcrs.employee.model.Phone;
import com.wcrs.employee.repository.EmployeeRepository;
import com.wcrs.employee.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableJpaAuditing //(auditorAwareRef = "auditorAware")
public class EmployeeApplication {

	private static final Logger log = LoggerFactory.getLogger(EmployeeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean
	CommandLineRunner init(EmployeeRepository employeeRepository, PhoneRepository phoneRepository, KafkaProducer kafkaProducer) {
		return args -> {
			var faker = new Faker();
			Random random = new Random();

			for (int i = 0; i <= 50; i++) {
				Employee employee = new Employee(
						null,
						getRandomDateBetween(LocalDate.of(1960, 1, 1), LocalDate.of(2005, 12, 31)),
						faker.name().firstName(),
						faker.name().lastName(),
						faker.name().username() + i,
						faker.regexify("[A-Za-z0-9]{14}"),
						Gender.valueOf(faker.options().option("MALE", "FEMALE")),
						i+faker.internet().emailAddress(),
						null,
						faker.job().title(),
						LocalDateTime.now(),null);

				employee = employeeRepository.save(employee); // Save first to assign ID

				int phoneCount = random.nextInt(1, 3);
				List<Phone> phones = new ArrayList<>();
				for (int j = 0; j < phoneCount; j++) {
					Phone phone = new Phone();
					phone.setNumber(faker.phoneNumber().subscriberNumber(9));
					phone.setPhoneCategory(PhoneCategory.valueOf(faker.options().option("MOBILE",  "WORK")));
					phone.setEmployee(employee);
					phones.add(phoneRepository.save(phone));
				}

				employee.setPhone(phones);
				employeeRepository.save(employee); // update with phone list
				System.out.println("Saved employee: " + employee.getFullName() + " with " + phones.size() + " phones");

				kafkaProducer.sendEvent(employee, EventType.CREATED);

				log.info("Employee created: {} " ,employee.getFullName());
			}

		};
	}

	private LocalDate getRandomDateBetween(LocalDate start, LocalDate end) {
		long days = ChronoUnit.DAYS.between(start, end);
		long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
		return start.plusDays(randomDays);
	}

}
