package com.wcrs.notification.config;


import com.google.protobuf.InvalidProtocolBufferException;
import com.wcrs.notification.NotificationRepository;
import com.wcrs.notification.enums.Status;
import com.wcrs.notification.model.Notification;
import employee.event.EmployeeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final NotificationRepository repository;
    private final RabbitMqPublisher rabbitMqPublisher;

    @KafkaListener(topics="employee-events", groupId = "employee-events-group")
    public void consumeEvent(byte[] event) {
        try {
            EmployeeEvent employeeEvent = EmployeeEvent.parseFrom(event);
            log.info("Received event: {}", employeeEvent.getFullName());

            Notification notification = Notification.builder()
                    .id(null)
                    .recipient(employeeEvent.getEmailAddress())
                    .subject(employeeEvent.getEventType() + " event for " + employeeEvent.getFullName())
                    .message("Dear " + employeeEvent.getFullName() + ",\n\n" + " Your account has been successfully created..\n\n")
                    .status(Status.PENDING)
                    .build();

            repository.save(notification);
            log.info("Notification saved to database");

            rabbitMqPublisher.sendNotification(notification);
            log.info("Notification sent to RabbitMQ");

        } catch (InvalidProtocolBufferException e) {
            log.error("Error in parsing event", e);
            throw new RuntimeException(e);

        }


    }
}
