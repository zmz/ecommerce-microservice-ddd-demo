package com.ecommerce.order.sdk.representation.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String productId;
    private int count;
    private BigDecimal itemPrice;
}
