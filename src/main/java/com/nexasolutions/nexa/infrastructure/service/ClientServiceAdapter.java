package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.domain.entity.Client;
import com.nexasolutions.nexa.domain.port.ClientServicePort;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateClientRequestDTO;
import com.nexasolutions.nexa.infrastructure.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class ClientServiceAdapter implements ClientServicePort {

    private final ClientRepository clientRepository;

    public ClientServiceAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    @Override
    public Client createClient(CreateClientRequestDTO request) {
        Client client = Client.builder()
                .name(request.name())
                .document(request.document())
                .email(request.email())
                .phone(request.phone())
                .createdAt(LocalDateTime.now())
                .build();

        return clientRepository.save(client);
    }
}
