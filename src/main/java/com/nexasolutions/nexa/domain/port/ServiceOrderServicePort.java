package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.application.dto.response.ServiceOrderResponseDTO;
import org.springframework.data.domain.Page;

public interface ServiceOrderServicePort {
    Page<ServiceOrderResponseDTO> getServiceOrders(int page, int size);
    ServiceOrderResponseDTO createServiceOrder(CreateServiceOrderRequestDTO request);
}
