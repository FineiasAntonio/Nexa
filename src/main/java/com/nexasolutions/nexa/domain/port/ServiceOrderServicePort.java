package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.application.dto.response.ServiceOrderResponseDTO;

public interface ServiceOrderServicePort {
    ServiceOrderResponseDTO createServiceOrder(CreateServiceOrderRequestDTO request);
}
