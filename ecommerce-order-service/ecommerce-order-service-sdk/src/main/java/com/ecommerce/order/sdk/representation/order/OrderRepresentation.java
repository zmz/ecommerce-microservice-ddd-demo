package com.ecommerce.order.sdk.representation.order;


import com.ecommerce.shared.model.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRepresentation {
    private String id;
    private List<OrderItem> items;
    private BigDecimal totalPrice;
    private String status;
    private Address address;
    private Instant createdAt;

}
