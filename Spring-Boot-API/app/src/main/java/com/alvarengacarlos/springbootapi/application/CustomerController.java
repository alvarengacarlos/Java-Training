package com.alvarengacarlos.springbootapi.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alvarengacarlos.springbootapi.domain.CustomerService;
import com.alvarengacarlos.springbootapi.domain.InvalidInputException;
import com.alvarengacarlos.springbootapi.domain.RegisterCustomerDto;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerCustomer(@RequestBody RegisterCustomerDto dto) throws InvalidInputException {
        customerService.registerCustomer(dto);
        return "Success";
    }
}
