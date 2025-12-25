package com.nexasolutions.nexa.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateServiceOrderRequestDTO(
        UUID existingClientId,
        @NotNull
        CreateClientRequestDTO client,
        @NotNull
        CreateEquipmentRequestDTO equipment
) {
}
