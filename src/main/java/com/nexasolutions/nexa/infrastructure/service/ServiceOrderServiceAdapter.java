package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.domain.entity.Client;
import com.nexasolutions.nexa.domain.entity.Equipment;
import com.nexasolutions.nexa.domain.entity.ServiceOrder;
import com.nexasolutions.nexa.domain.enums.ServiceOrderStatus;
import com.nexasolutions.nexa.domain.port.ClientServicePort;
import com.nexasolutions.nexa.domain.port.EquipmentServicePort;
import com.nexasolutions.nexa.domain.port.ServiceOrderServicePort;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.controller.dto.response.ServiceOrderResponseDTO;
import com.nexasolutions.nexa.infrastructure.repository.ServiceOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Optional;

@Service
@Slf4j
public class ServiceOrderServiceAdapter implements ServiceOrderServicePort {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ClientServicePort clientService;
    private final EquipmentServicePort equipmentService;

    public ServiceOrderServiceAdapter(
            ServiceOrderRepository serviceOrderRepository,
            ClientServicePort clientService,
            EquipmentServicePort equipmentService
    ) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.clientService = clientService;
        this.equipmentService = equipmentService;
    }

    @Override
    @Transactional
    public ServiceOrderResponseDTO createServiceOrder(CreateServiceOrderRequestDTO request) {

        Client client;

        if (request.existingClientId() != null) {
            client = clientService.getClientById(request.existingClientId());
        } else {
            client = clientService.createClient(request.client());
        }

        Equipment equipment = equipmentService.createEquipment(request.equipment());

        ServiceOrder newServiceOrder = new ServiceOrder();

        newServiceOrder.setPublicId(generatePublicId());

        newServiceOrder.setClient(client);
        newServiceOrder.setCreatedAt(LocalDateTime.now());
        newServiceOrder.setEquipment(equipment);
        newServiceOrder.setStatus(ServiceOrderStatus.OPEN);

        newServiceOrder = serviceOrderRepository.save(newServiceOrder);

        log.info("New service order created: {}", newServiceOrder.getPublicId());
        return newServiceOrder.toResponseDTO();
    }

    private int generatePublicId() {
        int currentYear = Year.now().getValue();

        int minId = currentYear * 100000;
        int maxId = (currentYear + 1) * 100000 - 1;

        Optional<Integer> maxPublicIdOpt = serviceOrderRepository.findMaxPublicId(minId, maxId);

        return maxPublicIdOpt.map(integer -> integer + 1).orElseGet(() -> minId + 1);
    }
}
