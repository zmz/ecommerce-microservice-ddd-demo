package com.ecommerce.order.query.representation;

import com.ecommerce.order.query.sdk.representation.order.OrderWithProductRepresentation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import lombok.SneakyThrows;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class OrderWithProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public OrderWithProductDao(NamedParameterJdbcTemplate jdbcTemplate,
                               ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    protected void save(OrderWithProductRepresentation order) {
        String sql = "INSERT INTO ORDER_WITH_PRODUCT (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";

        Map<String, String> paramMap = of("id",
                order.getId(),
                "json",
                objectMapper.writeValueAsString(order));

        jdbcTemplate.update(sql, paramMap);
    }

    public OrderWithProductRepresentation byId(String orderId) {
        try {
            String sql = "SELECT JSON_CONTENT FROM ORDER_WITH_PRODUCT WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", orderId), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException(orderId);
        }
    }

    private RowMapper<OrderWithProductRepresentation> mapper() {
        return (rs, rowNum) -> {
            try {
                return objectMapper.readValue(rs.getString("JSON_CONTENT"),
                        OrderWithProductRepresentation.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}
