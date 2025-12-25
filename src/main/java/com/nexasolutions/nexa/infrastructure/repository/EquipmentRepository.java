package com.nexasolutions.nexa.infrastructure.repository;

import com.nexasolutions.nexa.domain.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
}
