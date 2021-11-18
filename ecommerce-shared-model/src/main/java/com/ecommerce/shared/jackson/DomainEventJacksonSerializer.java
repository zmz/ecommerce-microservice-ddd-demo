package com.ecommerce.shared.jackson;

import java.io.IOException;
import java.util.Set;

import com.ecommerce.shared.event.DomainEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;


public class DomainEventJacksonSerializer extends BeanSerializerBase {

    public DomainEventJacksonSerializer(BeanSerializerBase source) {
        super(source);
    }

    private DomainEventJacksonSerializer(DomainEventJacksonSerializer source,
                                         ObjectIdWriter objectIdWriter) {
        super(source, objectIdWriter);
    }

    private DomainEventJacksonSerializer(DomainEventJacksonSerializer source,
                                         Set<String> toIgnore) {
        super(source, toIgnore);
    }

    private DomainEventJacksonSerializer(DomainEventJacksonSerializer domainEventSerializer, ObjectIdWriter objectIdWriter, Object filterId) {
        super(domainEventSerializer, objectIdWriter, filterId);
    }

    @Override
    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return new DomainEventJacksonSerializer(this, objectIdWriter);
    }

    @Override
    protected BeanSerializerBase withIgnorals(Set<String> toIgnore) {
        return new DomainEventJacksonSerializer(this, toIgnore);
    }

    @Override
    protected BeanSerializerBase withByNameInclusion(Set<String> set, Set<String> set1) {
        return null;
    }

    @Override
    protected BeanSerializerBase asArraySerializer() {
        return this;
    }

    @Override
    public BeanSerializerBase withFilterId(Object filterId) {
        return new DomainEventJacksonSerializer(this, _objectIdWriter, filterId);
    }

    @Override
    protected BeanSerializerBase withProperties(BeanPropertyWriter[] beanPropertyWriters, BeanPropertyWriter[] beanPropertyWriters1) {
        return null;
    }

    @Override
    public void serialize(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        serializeFields(bean, gen, provider);
        if (bean instanceof DomainEvent) {
            gen.writeStringField(DomainEventJacksonModule.TYPE, bean.getClass().getName());
        }
        gen.writeEndObject();
    }
}