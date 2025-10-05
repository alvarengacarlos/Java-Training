package com.alvarengacarlos.springbootapi.infra;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.alvarengacarlos.springbootapi.domain.Customer;

public class CustomerRepositoryImplTest {

    private static CustomerRepositoryImpl customerRepositoryImpl;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = DatabaseConnectionPool.INSTANCE.getDataSource();
        customerRepositoryImpl = new CustomerRepositoryImpl(dataSource);
    }

    @Test
    void shouldSaveACustomer() {
        final String phoneNumber = "+00 (00) 00000-0000";
        final String name = "John Doe";
        Customer customer = new Customer(name, phoneNumber);

        Assertions.assertDoesNotThrow(() -> customerRepositoryImpl.saveCustomer(customer));
    }
}
