package com.nexasolutions.nexa.infrastructure.application.dto.response;

import com.nexasolutions.nexa.domain.enums.ServiceOrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceOrderResponseDTO(
        UUID id,
        int publicId,
        ServiceOrderStatus status,
        LocalDateTime createdAt,
        boolean notificationSent
) {
}
