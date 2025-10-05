package com.alvarengacarlos.springbootapi.infra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.alvarengacarlos.springbootapi.domain.Customer;
import com.alvarengacarlos.springbootapi.domain.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final DataSource dataSource;

    public CustomerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveCustomer(Customer customer) {
        String insertDml = """
                INSERT INTO customers (id, name, phone_number) VALUES (?, ?, ?)
                        """;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertDml)) {
            preparedStatement.setObject(1, customer.id());
            preparedStatement.setString(2, customer.name());
            preparedStatement.setString(3, customer.phoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
