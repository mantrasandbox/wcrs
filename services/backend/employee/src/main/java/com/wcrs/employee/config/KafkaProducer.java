package com.wcrs.employee.config;

import com.wcrs.employee.enums.EventType;
import com.wcrs.employee.model.Employee;
import employee.event.EmployeeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendEvent(Employee employee,  EventType eventType){
        EmployeeEvent event = EmployeeEvent.newBuilder()
                .setFullName(employee.getFullName())
                .setDateOfBirth(employee.getDateOfBirth().toString())
                .setAge(String.valueOf(employee.getAge()))
                .setNIN(employee.getNIN())
                .setEmailAddress(employee.getEmail())
                .setJobTitle(employee.getJobTitle())
                .setPhoneNumber(employee.getPhone().toString())
                .setGender(employee.getGender().toString())
                .setEventType(eventType.toString())
                .build();

        try {
            kafkaTemplate.send("employee-events", event.toByteArray());
        } catch (Exception e) {
            log.error("Error in sending event: {}", event);
        }
    }




}
