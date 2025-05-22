package com.wcrs.notification.config;

import com.wcrs.notification.repository.NotificationRepository;
import com.wcrs.notification.enums.Status;
import com.wcrs.notification.model.Notification;
import com.wcrs.notification.sendgrid.SendGridService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RabbitMqConsumer {

    private final NotificationRepository repository;
    private final SendGridService sendGridService;

    @RabbitListener(queues="${rabbitmq.queues.email}")
    public void receiveNotification(Notification notification){
        log.info("Received notification: {}", notification.getRecipient());
        try{
            sendGridService.sendEmail(notification.getRecipient(),
                    notification.getSubject(),
                    notification.getMessage());
            log.info("Email sent to: {}", notification.getRecipient());

            notification.setStatus(Status.SUCCESS);

        }catch(Exception e){
            log.error("Error in sending email", e);
            notification.setRetries(notification.getRetries()+1);
            notification.setStatus(
            (notification.getRetries()<=3)?
            Status.RETRYING:Status.FAILED);
        }

        repository.save(notification);
    }

}
