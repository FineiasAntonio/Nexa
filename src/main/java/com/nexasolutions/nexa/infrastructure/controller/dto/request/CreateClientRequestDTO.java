package com.nexasolutions.nexa.infrastructure.controller.dto.request;

public record CreateClientRequestDTO(
        String name,
        String document,
        String email,
        String phone
) {
}
