package com.nexasolutions.nexa.domain.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Event {

    private LocalDateTime timestamp = LocalDateTime.now();

}
