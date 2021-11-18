package com.ecommerce.spring.common.event.messaging.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@ConfigurationProperties("ecommerce.kafka")
//@Validated
public class EcommerceKafkaProperties {


}
