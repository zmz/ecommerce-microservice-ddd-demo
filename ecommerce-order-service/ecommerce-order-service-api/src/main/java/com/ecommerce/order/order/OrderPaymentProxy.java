package com.ecommerce.order.order;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class OrderPaymentProxy {
    public void pay(String orderId, BigDecimal paidPrice) {
        //call remote payment service
    }
}
