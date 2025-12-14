package com.nexasolutions.nexa.infrastructure.controller.dto.request;

public record CreateEquipmentRequestDTO(
        String model,
        String manufacturer,
        String serialNumber,
        String comments
) {
}
