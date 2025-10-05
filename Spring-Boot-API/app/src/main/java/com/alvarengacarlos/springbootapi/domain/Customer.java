package com.alvarengacarlos.springbootapi.domain;

import java.util.UUID;

public record Customer(UUID id, String name, String phoneNumber) {

    public Customer(String name, String phoneNumber) {
        this(UUID.randomUUID(), name, phoneNumber);
    }
}
