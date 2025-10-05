package com.alvarengacarlos.springbootapi.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerServiceTest {

        private CustomerService customerService;
        private CustomerRepository customerRepository;

        @BeforeEach
        void beforeEach() {
                customerRepository = Mockito.mock();
                customerService = new CustomerService(customerRepository);
        }

        @Nested
        class RegisterCustomer {
                @Test
                void shouldRegisterACustomer() {
                        final String phoneNumber = "+00 (00) 00000-0000";
                        final String name = "John Doe";
                        RegisterCustomerDto registerCustomerDto = new RegisterCustomerDto(
                                        phoneNumber,
                                        name);

                        Assertions.assertDoesNotThrow(() -> customerService.registerCustomer(registerCustomerDto));

                        Mockito.verify(
                                        customerRepository,
                                        Mockito.times(1)).saveCustomer(Mockito.any(Customer.class));
                }
        }
}
