package com.nexasolutions.nexa.infrastructure.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String document,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone
) {}