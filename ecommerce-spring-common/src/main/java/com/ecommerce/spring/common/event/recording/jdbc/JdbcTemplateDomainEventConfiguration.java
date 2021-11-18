package com.ecommerce.spring.common.event.recording.jdbc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.ecommerce.shared.event.DomainEventConsumeRecorder;
import com.ecommerce.shared.event.DomainEventDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JdbcTemplateDomainEventConfiguration {

    @Bean
    public DomainEventDao domainEventDao(NamedParameterJdbcTemplate jdbcTemplate,
                                         ObjectMapper objectMapper) {
        return new JdbcTemplateDomainEventDao(jdbcTemplate, objectMapper);
    }

    @Bean
    public DomainEventConsumeRecorder domainEventConsumeRecorder(NamedParameterJdbcTemplate jdbcTemplate) {
        return new JdbcTemplateDomainEventConsumeRecorder(jdbcTemplate);
    }


    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
