package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.domain.entity.Client;
import com.nexasolutions.nexa.domain.entity.Equipment;
import com.nexasolutions.nexa.domain.entity.ServiceOrder;
import com.nexasolutions.nexa.domain.enums.ServiceOrderStatus;
import com.nexasolutions.nexa.domain.port.ClientServicePort;
import com.nexasolutions.nexa.domain.port.EquipmentServicePort;
import com.nexasolutions.nexa.domain.port.ServiceOrderServicePort;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.repository.ServiceOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ServiceOrder createServiceOrder(CreateServiceOrderRequestDTO request) {

        Client client;

        if (request.existingClientId() != null) {
            client = clientService.getClientById(request.existingClientId());
        } else {
            client = clientService.createClient(request.client());
        }

        Equipment equipment = equipmentService.createEquipment(request.equipment());

        ServiceOrder newServiceOrder = new ServiceOrder();

        newServiceOrder.setClient(client);
        newServiceOrder.setPublicId(1);
        newServiceOrder.setEquipment(equipment);
        newServiceOrder.setStatus(ServiceOrderStatus.OPEN);

        newServiceOrder = serviceOrderRepository.save(newServiceOrder);

        log.info("New service order created: {}", newServiceOrder.getPublicId());
        return newServiceOrder;
    }
}
