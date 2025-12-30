package com.nexasolutions.nexa.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String EXCHANGE_NAME = "nexa.exchange";

    public static final String NOTIFICATION_QUEUE_NAME = "notification.queue";
    public static final String SERVICE_ORDER_CREATED_ROUTING_KEY = "service-order.created";

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding serviceOrderCreatedBinding(Queue notificationQueue, TopicExchange exchange) {
        return BindingBuilder.bind(notificationQueue).to(exchange).with(SERVICE_ORDER_CREATED_ROUTING_KEY);
    }

    @Bean
    public JacksonJsonMessageConverter producerJackson2MessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
