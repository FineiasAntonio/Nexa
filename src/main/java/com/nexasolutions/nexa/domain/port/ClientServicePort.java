package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.domain.entity.Client;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateClientRequestDTO;

import java.util.UUID;

public interface ClientServicePort {
    Client getClientById(UUID id);
    Client createClient(CreateClientRequestDTO client);
}
