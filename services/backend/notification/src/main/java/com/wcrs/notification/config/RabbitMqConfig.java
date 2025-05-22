package com.wcrs.notification.config;
import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@Getter
public class RabbitMqConfig {

    @Value( "${rabbitmq.exchanges.direct}")
    private String EXCHANGE_NAME;

    @Value( "${rabbitmq.queues.email}")
    private String QUEUE_NAME;

    @Value( "${rabbitmq.routing-keys.email}")
    private String ROUTING_KEY;

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(this.EXCHANGE_NAME);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(this.QUEUE_NAME);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(emailQueue())
                .to(directExchange())
                .with(this.ROUTING_KEY);
    }

}
