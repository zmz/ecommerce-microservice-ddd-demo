package com.ecommerce.inventory.inventory;

import com.ecommerce.shared.model.BaseRepository;
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
public class InventoryRepository extends BaseRepository<Inventory> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public InventoryRepository(NamedParameterJdbcTemplate jdbcTemplate,
                               ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    protected void doSave(Inventory inventory) {
        String sql = "INSERT INTO INVENTORY (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", inventory.getId(), "json", objectMapper.writeValueAsString(inventory));
        jdbcTemplate.update(sql, paramMap);
    }


    public Inventory byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM INVENTORY WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new InventoryNotFoundException(id);
        }
    }

    private RowMapper<Inventory> mapper() {
        return (rs, rowNum) -> {
            try {
                return objectMapper.readValue(rs.getString("JSON_CONTENT"), Inventory.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    public Inventory byProductId(String productId) {
        try {
            String sql = "SELECT JSON_CONTENT FROM INVENTORY WHERE PRODUCT_ID=:productId;";
            return jdbcTemplate.queryForObject(sql, of("productId", productId), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new InventoryNotFoundByProductException(productId);
        }
    }
}
