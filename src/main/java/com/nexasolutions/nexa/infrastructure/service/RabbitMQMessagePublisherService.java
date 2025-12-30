package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.configuration.RabbitMQConfiguration;
import com.nexasolutions.nexa.domain.event.Event;
import com.nexasolutions.nexa.domain.port.MessagePublisherServicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQMessagePublisherService implements MessagePublisherServicePort {

    private final String EXCHANGE = RabbitMQConfiguration.EXCHANGE_NAME;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQMessagePublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(Event event) {
        log.info("Publishing event to RabbitMQ: {}", event.toString());
        String routingKey = RabbitMQConfiguration.SERVICE_ORDER_CREATED_ROUTING_KEY;
        rabbitTemplate.convertAndSend(EXCHANGE, routingKey, event);
    }
}
