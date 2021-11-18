package com.ecommerce.spring.common;

import java.io.IOException;
import java.time.Instant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.ALL;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.ecommerce.shared.jackson.DomainEventJacksonModule;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.logging.Level;
import lombok.extern.java.Log;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

@Log
@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper om = JsonMapper.builder()
        .defaultTimeZone(getTimeZone(of("Asia/Shanghai")))
        .visibility(ALL,NONE)
        .visibility(FIELD, ANY)
//                .configure(WRITE_DATES_AS_TIMESTAMPS, false)
        .addModule(trimStringModule())
        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
        .addModule(instantModule())
        .addModule(new DomainEventJacksonModule())
        .build();
        return  om;

    }

    private SimpleModule instantModule() {
        return new JavaTimeModule()
                .addSerializer(Instant.class, instantSerializer())
                .addDeserializer(Instant.class, instantDeserializer());
    }


    private SimpleModule trimStringModule() {
        return new SimpleModule()
                .addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
                    @Override
                    public String deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
                        log.log(Level.CONFIG,"----------------------------trimStringModule deserialize value is ----------------------------"+jsonParser.getValueAsString().trim());
                        return jsonParser.getValueAsString().trim();
                    }
                })
                .addSerializer(String.class, new StdScalarSerializer<String>(String.class) {
                    @Override
                    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                        log.log(Level.CONFIG,"----------------------------trimStringModule addSerializer value is ----------------------------"+value.trim());
                        gen.writeString(value.trim());
                    }
                });
    }

    private JsonDeserializer<Instant> instantDeserializer() {
        return new JsonDeserializer<Instant>() {
            @Override
            public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                log.log(Level.CONFIG, "----------------------------instantSerializer deserialize value is ----------------------------"+Instant.ofEpochMilli(p.getValueAsLong()));
                return Instant.ofEpochMilli(p.getValueAsLong());
            }
        };
    }

    private JsonSerializer<Instant> instantSerializer() {
        return new JsonSerializer<Instant>() {
            @Override
            public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                log.log(Level.CONFIG,"----------------------------instantSerializer serialize value is ----------------------------"+value.toEpochMilli());
                gen.writeNumber(value.toEpochMilli());

            }
        };
    }
}
