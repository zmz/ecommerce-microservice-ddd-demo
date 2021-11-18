package com.ecommerce.order.order.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.shared.model.Address;

@Component
public class OrderFactory {
    private final OrderIdGenerator idGenerator;

    public OrderFactory(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order create(List<OrderItem> items, Address address) {
        String orderId = idGenerator.generate();
        return Order.create(orderId, items, address);
    }
}
