package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.domain.entity.Customer;
import com.nexasolutions.nexa.domain.entity.Equipment;
import com.nexasolutions.nexa.domain.entity.ServiceOrder;
import com.nexasolutions.nexa.domain.enums.ServiceOrderStatus;
import com.nexasolutions.nexa.domain.event.ServiceOrderCreatedEvent;
import com.nexasolutions.nexa.domain.port.CustomerServicePort;
import com.nexasolutions.nexa.domain.port.EquipmentServicePort;
import com.nexasolutions.nexa.domain.port.MessagePublisherServicePort;
import com.nexasolutions.nexa.domain.port.ServiceOrderServicePort;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateServiceOrderRequestDTO;
import com.nexasolutions.nexa.infrastructure.application.dto.response.ServiceOrderResponseDTO;
import com.nexasolutions.nexa.infrastructure.repository.ServiceOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Optional;

@Service
@Slf4j
public class ServiceOrderServiceAdapter implements ServiceOrderServicePort {

    private final ServiceOrderRepository serviceOrderRepository;
    private final CustomerServicePort customerService;
    private final EquipmentServicePort equipmentService;
    private final MessagePublisherServicePort messagePublisherServicePort;

    public ServiceOrderServiceAdapter(
            ServiceOrderRepository serviceOrderRepository,
            CustomerServicePort customerService,
            EquipmentServicePort equipmentService,
            MessagePublisherServicePort messagePublisherServicePort) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.customerService = customerService;
        this.equipmentService = equipmentService;
        this.messagePublisherServicePort = messagePublisherServicePort;
    }

    @Override
    public Page<ServiceOrderResponseDTO> getServiceOrders(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return serviceOrderRepository.findAll(pageable).map(ServiceOrder::toResponseDTO);
    }

    @Override
    @Transactional
    public ServiceOrderResponseDTO createServiceOrder(CreateServiceOrderRequestDTO request) {

        Customer customer;

        if (request.existingCustomerId() != null) {
            customer = customerService.getCustomerById(request.existingCustomerId());
        } else {
            customer = customerService.createCustomer(request.customer());
        }

        Equipment equipment = equipmentService.createEquipment(request.equipment());

        ServiceOrder newServiceOrder = new ServiceOrder();

        newServiceOrder.setPublicId(generatePublicId());

        newServiceOrder.setCustomer(customer);
        newServiceOrder.setCreatedAt(LocalDateTime.now());
        newServiceOrder.setEquipment(equipment);
        newServiceOrder.setStatus(ServiceOrderStatus.OPEN);

        newServiceOrder = serviceOrderRepository.save(newServiceOrder);

        log.info("New service order created: {}", newServiceOrder.getPublicId());

        try {
            ServiceOrderCreatedEvent event = ServiceOrderCreatedEvent.builder()
                    .serviceOrderPublicId(newServiceOrder.getPublicId())
                    .customerName(customer.getName())
                    .customerEmail(customer.getEmail())
                    .customerPhone(customer.getPhone())
                    .build();
            messagePublisherServicePort.publish(event);
        } catch (Exception e) {
            log.error("Failed to create notification: {}", e.getMessage());
            return newServiceOrder.toResponseDTO(false);
        }

        return newServiceOrder.toResponseDTO(true);
    }

    private int generatePublicId() {
        int currentYear = Year.now().getValue();

        int minId = currentYear * 100000;
        int maxId = (currentYear + 1) * 100000 - 1;

        Optional<Integer> maxPublicIdOpt = serviceOrderRepository.findMaxPublicId(minId, maxId);

        return maxPublicIdOpt.map(integer -> integer + 1).orElseGet(() -> minId + 1);
    }
}
