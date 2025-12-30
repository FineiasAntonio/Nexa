package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.domain.event.Event;

public interface MessagePublisherServicePort {
    void publish(Event event);
}
