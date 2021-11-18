package com.ecommerce.order.order.model;

import org.springframework.stereotype.Component;

import com.ecommerce.shared.utils.UuidGenerator;

@Component
public class OrderIdGenerator {

    public String generate() {
        return UuidGenerator.newUuid();
    }
}
