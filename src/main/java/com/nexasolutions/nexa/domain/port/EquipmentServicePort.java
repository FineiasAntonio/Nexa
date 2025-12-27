package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.domain.entity.Equipment;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateEquipmentRequestDTO;

public interface EquipmentServicePort {
    Equipment createEquipment(CreateEquipmentRequestDTO request);
}
