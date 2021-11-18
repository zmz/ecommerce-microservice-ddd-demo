package com.ecommerce.product.sdk.representation.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummaryRepresentation {
    private String id;
    private String name;
    private BigDecimal price;

}
