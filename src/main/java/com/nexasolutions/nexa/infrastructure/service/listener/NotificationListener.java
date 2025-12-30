package com.nexasolutions.nexa.infrastructure.service.listener;

import com.nexasolutions.nexa.configuration.RabbitMQConfiguration;
import com.nexasolutions.nexa.domain.event.ServiceOrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    @RabbitListener(queues = RabbitMQConfiguration.NOTIFICATION_QUEUE_NAME)
    public void onServiceOrderCreated(ServiceOrderCreatedEvent message) {
        log.info("Mensagem recebida: {}", message.toString());
    }

}
