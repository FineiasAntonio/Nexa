package com.nexasolutions.nexa.infrastructure.repository;

import com.nexasolutions.nexa.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
