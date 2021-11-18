package com.ecommerce.product.sdk.representation.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRepresentation {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;
    private int inventory;
    private String categoryId;

}
