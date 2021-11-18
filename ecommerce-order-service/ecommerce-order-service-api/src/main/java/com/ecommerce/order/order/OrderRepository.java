package com.ecommerce.order.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.ecommerce.order.order.exception.OrderNotFoundException;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.shared.model.BaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class OrderRepository extends BaseRepository<Order> {
    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public OrderRepository(NamedParameterJdbcTemplate jdbcTemplate,
                           ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    protected void doSave(Order order) {
        String sql = "INSERT INTO ORDERS (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", order.getId(), "json", objectMapper.writeValueAsString(order));
        jdbcTemplate.update(sql, paramMap);
    }

    public Order byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM ORDERS WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException(id);
        }
    }

    private RowMapper<Order> mapper() {
        return (rs, rowNum) -> {
            try {
                return objectMapper.readValue(rs.getString("JSON_CONTENT"), Order.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

}
