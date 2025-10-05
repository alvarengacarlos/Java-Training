package com.alvarengacarlos.springbootapi.domain;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(RegisterCustomerDto registerCustomerDto) throws InvalidInputException {
        Validator.validate(registerCustomerDto);
        Customer customer = new Customer(registerCustomerDto.name(), registerCustomerDto.phoneNumber());
        customerRepository.saveCustomer(customer);
    }
}
