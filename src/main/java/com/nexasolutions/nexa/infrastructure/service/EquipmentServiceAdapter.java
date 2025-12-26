package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.domain.entity.Equipment;
import com.nexasolutions.nexa.domain.port.EquipmentServicePort;
import com.nexasolutions.nexa.infrastructure.controller.dto.request.CreateEquipmentRequestDTO;
import com.nexasolutions.nexa.infrastructure.repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EquipmentServiceAdapter implements EquipmentServicePort {

    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceAdapter(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment createEquipment(CreateEquipmentRequestDTO request) {
        Equipment equipment = Equipment.builder()
                .model(request.model())
                .manufacturer(request.manufacturer())
                .serialNumber(request.serialNumber())
                .comments(request.comments())
                .build();

        return equipmentRepository.save(equipment);
    }
}
