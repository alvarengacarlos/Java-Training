package com.alvarengacarlos.springbootapi;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import com.alvarengacarlos.springbootapi.domain.RegisterCustomerDto;

@DisplayName("/customers")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class CustomerTest {
    private static RestClient restClient;

    @BeforeAll
    static void beforeAll() throws IOException {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/customers")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Nested
    @Order(2)
    @DisplayName("/")
    class RegisterCustomer {

        @Test
        void shouldReturnBadRequestHttpResponse() {
            RegisterCustomerDto registerCustomerDto = new RegisterCustomerDto("", "");
            ResponseEntity<Void> responseEntity = restClient.post()
                    .uri("/")
                    .body(registerCustomerDto)
                    .retrieve()
                    .onStatus((response) -> response.getStatusCode().is4xxClientError())
                    .toBodilessEntity();

            Assertions.assertEquals(400, responseEntity.getStatusCode().value());
        }

        @Test
        void shouldReturnCreatedHttpResponse() {
            final String phoneNumber = "+00 (00) 00000-0000";
            final String name = "John Doe";

            RegisterCustomerDto registerCustomerDto = new RegisterCustomerDto(
                    phoneNumber,
                    name);
            ResponseEntity<Void> responseEntity = restClient.post()
                    .uri("/")
                    .body(registerCustomerDto)
                    .retrieve()
                    .toBodilessEntity();

            Assertions.assertEquals(201, responseEntity.getStatusCode().value());
        }
    }
}
