package com.learning.util;

import com.learning.DAO.PersonDAO;
import com.learning.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        Person person = (Person)target;
        if (this.personDAO.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Such person already exists");
        }

    }
}