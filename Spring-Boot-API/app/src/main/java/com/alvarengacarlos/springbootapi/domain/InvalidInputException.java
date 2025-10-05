package com.alvarengacarlos.springbootapi.domain;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
