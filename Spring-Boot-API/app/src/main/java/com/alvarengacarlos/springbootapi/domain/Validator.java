package com.alvarengacarlos.springbootapi.domain;

import java.util.Iterator;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class Validator {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> void validate(T dto) throws InvalidInputException {
        jakarta.validation.Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator
                .validate(dto);
        if (!constraintViolations.isEmpty()) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                ConstraintViolation<T> constraintViolation = iterator.next();
                stringBuilder.append(constraintViolation.getPropertyPath());
                stringBuilder.append(" -> ");
                stringBuilder.append(constraintViolation.getMessage());
                stringBuilder.append(";");
            }

            throw new InvalidInputException(stringBuilder.toString());
        }
    }
}
