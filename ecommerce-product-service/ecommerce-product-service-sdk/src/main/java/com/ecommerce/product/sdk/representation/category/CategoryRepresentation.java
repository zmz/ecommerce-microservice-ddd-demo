package com.ecommerce.product.sdk.representation.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRepresentation {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
}
