package com.ecommerce.spring.common.event.messaging.rabbit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RabbitListener(queues = {"#{'${ecommerce.rabbit.receiveQ}'}"})
public @interface EcommerceRabbitListener {
}
