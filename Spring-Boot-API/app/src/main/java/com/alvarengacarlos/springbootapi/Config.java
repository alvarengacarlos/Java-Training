package com.alvarengacarlos.springbootapi;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alvarengacarlos.springbootapi.domain.CustomerRepository;
import com.alvarengacarlos.springbootapi.domain.CustomerService;
import com.alvarengacarlos.springbootapi.infra.DatabaseConnectionPool;

@Configuration
public class Config {
    @Bean
    public CustomerService customerService(CustomerRepository customerRepository) {
        return new CustomerService(customerRepository);
    }

    @Bean
    public DataSource databaseConnectionPool() {
        return DatabaseConnectionPool.INSTANCE.getDataSource();
    }
}
