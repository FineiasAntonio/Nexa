package com.nexasolutions.nexa.domain.entity;

import com.nexasolutions.nexa.utils.UUIDV7;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue(generator = "uuidv7")
    @UUIDV7
    private UUID id;

    private String name;
    private String document;
    private String email;
    private String phone;

    private LocalDateTime createdAt;
}
