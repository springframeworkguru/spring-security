package com.agharibi.commands.validators;


import com.agharibi.commands.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerForm customerForm = (CustomerForm) target;

        if(!customerForm.getPasswordText().equals(customerForm.getPasswordTextConf())) {
            errors.rejectValue("passwordText", "Password does not match");
            errors.rejectValue("passwordTextConf",  "Password does not match");
        }
    }
}
