package com.nexasolutions.nexa.infrastructure.repository;

import com.nexasolutions.nexa.domain.entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {

    @Query("SELECT MAX(so.publicId) FROM ServiceOrder so WHERE (so.publicId >= :minId) AND (so.publicId <= :maxId)")
    Optional<Integer> findMaxPublicId(int minId, int maxId);

}
