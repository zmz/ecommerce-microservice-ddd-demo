package com.ecommerce.product.sdk.event.product;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;

@Getter
@Setter
public class ProductCreatedEvent extends ProductEvent {
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;


    @ConstructorProperties({"productId", "name", "description", "price", "createdAt"})
    public ProductCreatedEvent(String productId, String name, String description, BigDecimal price, Instant createdAt) {
        super(productId);
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }
}
