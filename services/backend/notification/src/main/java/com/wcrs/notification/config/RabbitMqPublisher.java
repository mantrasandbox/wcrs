package com.wcrs.notification.config;

import com.wcrs.notification.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfig rabbitMqConfig;

    public void sendNotification(Notification notification){
        String exchangeName = rabbitMqConfig.getEXCHANGE_NAME();
        String routingKey = rabbitMqConfig.getROUTING_KEY();

        rabbitTemplate.convertAndSend(exchangeName, routingKey, notification);
    }
}
