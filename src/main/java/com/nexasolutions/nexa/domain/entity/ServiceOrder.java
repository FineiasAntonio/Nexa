package com.nexasolutions.nexa.domain.entity;

import com.nexasolutions.nexa.domain.enums.ServiceOrderStatus;
import com.nexasolutions.nexa.infrastructure.application.dto.response.ServiceOrderResponseDTO;
import com.nexasolutions.nexa.utils.UUIDV7;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrder {

    @Id
    @GeneratedValue(generator = "uuidv7")
    @UUIDV7
    private UUID id;

    @Column(unique = true)
    private int publicId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus status;

    private LocalDateTime createdAt;

    public ServiceOrderResponseDTO toResponseDTO() {
        return new ServiceOrderResponseDTO(
                this.id,
                this.publicId,
                this.status,
                this.createdAt
        );
    }


}
