package com.alvarengacarlos.springbootapi.domain;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record RegisterCustomerDto(
        @NotBlank @Length(max = 30) String phoneNumber,
        @NotBlank @Length(max = 100) String name) {

}
