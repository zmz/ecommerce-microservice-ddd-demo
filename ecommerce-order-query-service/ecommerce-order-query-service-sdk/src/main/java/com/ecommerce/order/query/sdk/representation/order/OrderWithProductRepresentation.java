package com.ecommerce.order.query.sdk.representation.order;

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
public class OrderWithProductRepresentation {
    private String id;
    private BigDecimal totalPrice;
    private String status;
    private Instant createdAt;
    private Address address;
    private List<Product> products;
}
