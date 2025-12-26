package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.controller.dto.response.ServiceOrderResponseDTO;

public interface ServiceOrderServicePort {
    ServiceOrderResponseDTO createServiceOrder(CreateServiceOrderRequestDTO request);
}
