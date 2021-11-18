package com.ecommerce.spring.common.event;

import org.springframework.scheduling.annotation.Scheduled;

import com.ecommerce.shared.event.DomainEventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainEventBackupPublishScheduler {

    private DomainEventPublisher publisher;

    public DomainEventBackupPublishScheduler(DomainEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 120000)
    public void run() {
        log.info("Scheduled trigger domain event backup publish process.");
        publisher.publishNextBatch();
    }

}
