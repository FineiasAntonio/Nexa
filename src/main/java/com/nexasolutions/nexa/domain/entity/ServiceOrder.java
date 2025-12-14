package com.nexasolutions.nexa.domain.entity;

import com.nexasolutions.nexa.infrastructure.controller.dto.response.ServiceOrderResponseDTO;
import com.nexasolutions.nexa.utils.UUIDV7;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrder {

    @Id
    @GeneratedValue(generator = "uuidv7")
    @UUIDV7
    private UUID id;

    private int pulicId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Client client;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Equipment equipment;

    private LocalDateTime createdAt;

    toResponseDTO() {
        return new ServiceOrderResponseDTO(
                this.id,
                this.pulicId,
                this.createdAt
        );
    }


}
