package org.profit.avia.validator;

import org.profit.avia.model.City;
import org.profit.avia.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class CityValidator implements Validator {

    @Autowired
    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> aClass) {
        return City.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Set<ConstraintViolation<Object>> validates =  validator.validate(o);
        ValidateUtils.fillErrors(errors, validates);
    }
}
