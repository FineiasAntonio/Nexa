package com.nexasolutions.nexa.infrastructure.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateItemRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String SKU,
        @PositiveOrZero
        int storedQuantity,
        @NotNull
        @PositiveOrZero
        BigDecimal price
) {}
