package com.nexasolutions.nexa.infrastructure.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateServiceOrderRequestDTO(
        UUID existingCustomerId,
        @NotNull
        CreateCustomerRequestDTO customer,
        @NotNull
        CreateEquipmentRequestDTO equipment
) {
}
