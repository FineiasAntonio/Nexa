package com.nexasolutions.nexa.infrastructure.application.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemResponseDTO(
        UUID id,
        String name,
        String SKU,
        int storedQuantity,
        BigDecimal price
) {}
