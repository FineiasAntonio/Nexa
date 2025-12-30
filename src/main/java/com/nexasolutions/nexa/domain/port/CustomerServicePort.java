package com.nexasolutions.nexa.domain.port;

import com.nexasolutions.nexa.domain.entity.Customer;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateCustomerRequestDTO;

import java.util.UUID;

public interface CustomerServicePort {
    Customer getCustomerById(UUID id);
    Customer createCustomer(CreateCustomerRequestDTO customer);
}
