package com.nexasolutions.nexa.infrastructure.service;

import com.nexasolutions.nexa.domain.entity.Customer;
import com.nexasolutions.nexa.domain.port.CustomerServicePort;
import com.nexasolutions.nexa.infrastructure.application.dto.request.CreateCustomerRequestDTO;
import com.nexasolutions.nexa.infrastructure.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceAdapter implements CustomerServicePort {

    private final CustomerRepository customerRepository;

    public CustomerServiceAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public Customer createCustomer(CreateCustomerRequestDTO request) {
        Customer customer = Customer.builder()
                .name(request.name())
                .document(request.document())
                .email(request.email())
                .phone(request.phone())
                .createdAt(LocalDateTime.now())
                .build();

        return customerRepository.save(customer);
    }
}
