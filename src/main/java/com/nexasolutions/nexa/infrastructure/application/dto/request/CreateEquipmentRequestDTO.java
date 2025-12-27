package com.nexasolutions.nexa.infrastructure.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateEquipmentRequestDTO(
        @NotBlank
        String model,
        String manufacturer,
        String serialNumber,
        String comments
) {
}
