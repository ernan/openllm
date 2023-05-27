package com.fodala.web.api;

import com.fodala.pojo.Individual;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class IndividualValdator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return cls == Individual.class;
    }

    @Override
    public void validate(Object ip, Errors errors) {
        Individual individual = (Individual) ip;
        if (individual.getName().length() > 25) {
            errors.reject("name", "Name length cannot be more than 25 characters.");
        }
        if (!individual.getPhone().startsWith("08")) {
            errors.reject("Phone", "Phone must start with 08.");
        }
    }

}
