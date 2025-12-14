package com.nexasolutions.nexa.infrastructure.controller.dto.request;

public record CreateServiceOrderRequestDTO(
        CreateClientRequestDTO client,
        CreateEquipmentRequestDTO equipment
) {
}
