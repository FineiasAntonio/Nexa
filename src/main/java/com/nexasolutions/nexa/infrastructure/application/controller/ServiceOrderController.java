package com.nexasolutions.nexa.infrastructure.application.controller;

import com.nexasolutions.nexa.domain.port.ServiceOrderServicePort;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.application.dto.response.ServiceOrderResponseDTO;
import com.nexasolutions.nexa.utils.ApiDefinitions;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiDefinitions.API_VERSION + ApiDefinitions.RESOURCE_SERVICE_ORDERS)
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