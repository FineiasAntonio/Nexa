package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.domain.entity.ServiceOrder;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateServiceOrderRequestDTO;

public interface ServiceOrderServicePort {
    ServiceOrder createServiceOrder(CreateServiceOrderRequestDTO request);
}
