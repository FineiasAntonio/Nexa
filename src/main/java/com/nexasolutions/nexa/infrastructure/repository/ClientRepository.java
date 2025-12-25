package com.nexasolutions.nexa.infrastructure.repository;

import com.nexasolutions.nexa.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
