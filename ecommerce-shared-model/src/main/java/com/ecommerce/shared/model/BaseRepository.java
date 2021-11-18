package com.ecommerce.shared.model;


import javax.inject.Inject;

import com.ecommerce.shared.event.DomainEventDao;

public abstract class BaseRepository<AR extends BaseAggregate> {

    @Inject
    private DomainEventDao eventDao;

    public final void save(AR aggregate) {
        eventDao.save(aggregate.get_events());
        aggregate.clearEvents();
        doSave(aggregate);
    }

    protected abstract void doSave(AR aggregate);
}
