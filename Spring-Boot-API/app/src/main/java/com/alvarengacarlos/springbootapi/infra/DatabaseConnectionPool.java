package com.alvarengacarlos.springbootapi.infra;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnectionPool {
    public static final DatabaseConnectionPool INSTANCE = new DatabaseConnectionPool();
    private final HikariDataSource hikariDataSource;

    private DatabaseConnectionPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/spring_boot_api");
        hikariConfig.setUsername("pg");
        hikariConfig.setPassword("pgpw");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public DataSource getDataSource() {
        return hikariDataSource;
    }
}
