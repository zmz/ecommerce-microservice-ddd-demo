package com.ecommerce.order;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

import com.ecommerce.order.order.OrderEventHandler;
import com.ecommerce.order.sdk.event.order.OrderEvent;
import com.ecommerce.spring.common.event.messaging.rabbit.EcommerceRabbitListener;

@Component
@EcommerceRabbitListener
public class OrderRabbitListener {
    private OrderEventHandler eventHandler;

    public OrderRabbitListener(OrderEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @RabbitHandler
    public void on(OrderEvent event) {
        eventHandler.cqrsSync(event);
    }

}
