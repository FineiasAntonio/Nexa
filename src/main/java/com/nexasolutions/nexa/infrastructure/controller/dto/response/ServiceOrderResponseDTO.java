package com.nexasolutions.nexa.infrastructure.controller.dto.response;

import java.time.LocalDateTime;

public record ServiceOrderResponseDTO(
        Long id,
        String status,
        LocalDateTime createdAt
) {
}
