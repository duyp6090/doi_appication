package com.duydev.backend.util.anotation.validation;

import java.util.List;

import com.duydev.backend.util.anotation.anotationpattern.EnumPattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<EnumPattern, Enum<?>> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumPattern constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNull or @NotEmpty should handle nulls
        }
        return acceptedValues.contains(value.name());
    }
}
