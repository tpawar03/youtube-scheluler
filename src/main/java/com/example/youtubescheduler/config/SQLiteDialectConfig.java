package com.example.youtubescheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.AnsiDialect;

@Configuration
public class SQLiteDialectConfig {

    @Bean
    public Dialect jdbcDialect() {
        // Use ANSI as fallback — works well for SQLite basic queries
        return AnsiDialect.INSTANCE;
    }
}
