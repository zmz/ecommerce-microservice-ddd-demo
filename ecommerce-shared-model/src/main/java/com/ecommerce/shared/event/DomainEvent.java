package com.ecommerce.shared.event;


import java.time.Instant;

import com.ecommerce.shared.utils.UuidGenerator;

import lombok.Getter;

import static java.time.LocalDateTime.now;


@Getter
public abstract class DomainEvent {
    private String _id = UuidGenerator.newUuid();
    private Instant _createdAt = Instant.now();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + _id + "]";
    }

}
