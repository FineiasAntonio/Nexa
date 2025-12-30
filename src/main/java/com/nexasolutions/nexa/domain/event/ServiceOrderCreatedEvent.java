package com.nexasolutions.nexa.domain.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrderCreatedEvent extends Event {

    private int serviceOrderPublicId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

}