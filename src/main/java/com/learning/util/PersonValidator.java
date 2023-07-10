package com.learning.util;

import com.learning.DAO.PersonDAO;
import com.learning.models.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Such person already exists");
        }
    }
}
