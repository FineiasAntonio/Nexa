package com.nexasolutions.nexa.infrastructure.controller.api;

import com.nexasolutions.nexa.domain.entity.ServiceOrder;
import com.nexasolutions.nexa.domain.port.ServiceOrderServicePort;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.controller.dto.response.ServiceOrderResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    private final ServiceOrderServicePort serviceOrderServicePort;

    public ServiceOrderController(ServiceOrderServicePort serviceOrderServicePort) {
        this.serviceOrderServicePort = serviceOrderServicePort;
    }

    @PostMapping
    public ServiceOrderResponseDTO createServiceOrder(@Valid @RequestBody CreateServiceOrderRequestDTO request) {
        return serviceOrderServicePort.createServiceOrder(request);
    }

}