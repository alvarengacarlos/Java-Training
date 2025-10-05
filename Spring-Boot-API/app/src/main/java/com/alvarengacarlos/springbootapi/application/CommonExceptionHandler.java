package com.alvarengacarlos.springbootapi.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alvarengacarlos.springbootapi.domain.InvalidInputException;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidInputException(InvalidInputException exception) {
        return exception.getMessage();
    }
}
