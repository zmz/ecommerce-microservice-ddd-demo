package com.ecommerce.spring.common.event.recording.jdbc;

import java.util.List;
import java.util.function.Function;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.ecommerce.shared.event.DomainEvent;
import com.ecommerce.shared.event.DomainEventDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

@Slf4j
public class JdbcTemplateDomainEventDao implements DomainEventDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final RowMapper mapper;

    public JdbcTemplateDomainEventDao(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.mapper = eventMapper(objectMapper);
    }

    @Override
    public void save(List<DomainEvent> events) {
        String sql = "INSERT INTO DOMAIN_EVENT (ID, JSON_CONTENT) VALUES (:id, :json);";
        SqlParameterSource[] parameters = events.stream()
                .map((Function<DomainEvent, SqlParameterSource>) domainEvent ->
                {
                    try {
                        return new MapSqlParameterSource()
                                .addValue("id", domainEvent.get_id())
                                .addValue("json", objectMapper.writeValueAsString(domainEvent));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .toArray(SqlParameterSource[]::new);



        jdbcTemplate.batchUpdate(sql, parameters);

    }

    @Override
    public void delete(String eventId) {
        String sql = "DELETE FROM DOMAIN_EVENT WHERE ID = :id;";
        jdbcTemplate.update(sql, of("id", eventId));
    }

    @Override
    public DomainEvent get(String eventId) {
        String sql = "SELECT JSON_CONTENT FROM DOMAIN_EVENT WHERE ID = :id;";
        return (DomainEvent) jdbcTemplate.queryForObject(sql, of("id", eventId), mapper);
    }

    @Override
    public List<DomainEvent> nextPublishBatch(int size) {
        String sql = "SELECT JSON_CONTENT FROM DOMAIN_EVENT WHERE STATUS != 'FAILED' ORDER BY CREATED_AT LIMIT :limit;";
        return jdbcTemplate.query(sql, of("limit", size), mapper);
    }

    @Override
    public void markAsPublished(String eventId) {
        delete(eventId);
    }

    @Override
    public void markAsPublishFailed(String eventId) {
        String sql = "UPDATE DOMAIN_EVENT SET STATUS = 'FAILED' WHERE ID = :id;";
        jdbcTemplate.update(sql, of("id", eventId));
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM DOMAIN_EVENT;";
        jdbcTemplate.update(sql, newHashMap());
    }

    private RowMapper eventMapper(ObjectMapper objectMapper) {
        return (rs, rowNum) -> {
            try {
                return objectMapper.readValue(rs.getString("JSON_CONTENT"), DomainEvent.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.info(e.getMessage());
            }
            return null;
        };
    }
}

