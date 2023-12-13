package com.sires.mp.validations;


import com.sires.mp.validations.interfaces.Ci;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;


public class CiValidator  implements ConstraintValidator<Ci, String> {

    @Override
    public void initialize(Ci constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cedula, ConstraintValidatorContext constraintValidatorContext) {
        return validateCi(cedula);
    }

    public boolean validateCi(String ci) {
        validateInput(ci);
        String cleanCi = this.cleanCi(ci);
        char validationDigit = cleanCi.charAt(cleanCi.length() - 1);

        return Character.getNumericValue(validationDigit) == this.validationDigit(cleanCi);
    }

    public boolean validateInput(String ci) {
        if (Objects.isNull(ci))
           return false;
        else if (ci.isEmpty())
           return true;
        return true;
    }

    public String cleanCi(String ci) {
        return ci.replaceAll("[^0-9]", "");
    }

    /**
     * Check validation digit from a number of ci
     *
     * @param ci String
     * @return Integer
     */
    protected Integer validationDigit(String ci) {
        String cleanCi = this.cleanCi(ci);
        int a = 0;
        String baseNumber = "2987634";
        String addZeros = String.format("%8s", cleanCi).replace(" ", "0");

        for (int i = 0; i < baseNumber.length(); i++) {
            int baseDigit = Character.getNumericValue(baseNumber.charAt(i));
            int ciWithZeros = Character.getNumericValue(addZeros.charAt(i));
            a += (baseDigit * ciWithZeros) % 10;
        }
        return a % 10 == 0 ? 0 : 10 - a % 10;
    }
}
