package com.ecommerce.product.product;

import com.ecommerce.shared.model.BaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import static com.google.common.collect.ImmutableMap.of;

@Component
@Log
public class ProductRepository extends BaseRepository<Product> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate,
                             ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    protected void doSave(Product product) {
        String sql = "INSERT INTO PRODUCT (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", product.getId(), "json", objectMapper.writeValueAsString(product));
        jdbcTemplate.update(sql, paramMap);
    }

    public Product byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM PRODUCT WHERE ID=:id;";
            return (Product) jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
            log.info(e.getMessage());
            throw new ProductNotFoundException(id,e);
        }
    }

    private RowMapper mapper() {
        return (rs, rowNum) -> {
            try {
                return objectMapper.readValue(rs.getString("JSON_CONTENT"), Product.class);
            } catch (JsonProcessingException e) {
                log.info(e.getMessage());
            }
            return null;
        };
    }

}
