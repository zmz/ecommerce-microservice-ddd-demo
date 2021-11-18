package com.ecommerce.shared.jackson;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.ecommerce.shared.event.DomainEvent;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DomainEventJacksonDeserializer extends StdDeserializer<DomainEvent> {

    public DomainEventJacksonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DomainEvent deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode obj = mapper.readTree(jp);
        Iterator<Map.Entry<String, JsonNode>> elementsIterator = obj.fields();
        while (elementsIterator.hasNext()) {
            Map.Entry<String, JsonNode> element = elementsIterator.next();
            String name = element.getKey();
            if (name.equals(DomainEventJacksonModule.TYPE)) {
                try {
                    JsonNode value = element.getValue();
                    String className = value.asText();
                    Class<?> aClass = Class.forName(className);
                    return (DomainEvent) mapper.treeToValue(obj, aClass);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("No meta filed (_type) found in JSON string.");

    }
}
