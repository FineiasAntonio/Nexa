package com.nexasolutions.nexa.domain.entity;

import com.nexasolutions.nexa.utils.UUIDV7;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(generator = "uuidv7")
    @UUIDV7
    private UUID id;

    private String model;

    @Nullable
    private String manufacturer;

    @Nullable
    private String serialNumber;

    @Nullable
    private String comments;

}
