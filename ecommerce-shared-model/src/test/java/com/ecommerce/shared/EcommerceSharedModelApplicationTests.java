package com.ecommerce.shared;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ecommerce.shared.event.DomainEvent;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration
class EcommerceSharedModelApplicationTests {

    @Test
    public void shouldCreateEvent() {
        DomainEvent domainEvent = new DomainEvent() {
        };

        assertNotNull(domainEvent);
    }

}
