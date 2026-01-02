package com.nexasolutions.nexa.domain.entity;

import com.nexasolutions.nexa.utils.UUIDV7;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(generator = "uuidv7")
    @UUIDV7
    private UUID id;

    private String name;
    private String SKU;
    private int storedQuantity;
    private BigDecimal price;

}
