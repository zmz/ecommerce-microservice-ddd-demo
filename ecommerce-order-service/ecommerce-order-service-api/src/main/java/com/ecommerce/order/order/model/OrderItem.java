package com.ecommerce.order.order.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String productId;
    private int count;
    private BigDecimal itemPrice;

    public static OrderItem create(String productId, int count, BigDecimal itemPrice) {
        return OrderItem.builder()
                .productId(productId)
                .count(count)
                .itemPrice(itemPrice)
                .build();
    }

    BigDecimal totalPrice() {
        return itemPrice.multiply(BigDecimal.valueOf(count));
    }

    public void updateCount(int count) {
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }
}
