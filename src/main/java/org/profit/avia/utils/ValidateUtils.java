package org.profit.avia.utils;

import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidateUtils {
    public static void fillErrors(Errors errors, Set<ConstraintViolation<Object>> validates){
        for (ConstraintViolation<Object> violation: validates){
            errors.reject(violation.getPropertyPath() + " " + violation.getMessage());
        }
    }
}
