package com.nexasolutions.nexa.infrastructure.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequestDTO(
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
