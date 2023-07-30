package com.learning.controllers;

import com.learning.DAO.PersonDAO;
import com.learning.models.Person;
import com.learning.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/people"})
public class PersonController {
    private final PersonDAO personDAO;
    private final PersonValidator validator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator validator) {
        this.personDAO = personDAO;
        this.validator = validator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("personList", this.personDAO.index());
        return "people/index";
    }

    @GetMapping({"/{personId}"})
    public String show(@PathVariable("personId") int personId, Model model) {
        model.addAttribute("person", this.personDAO.show(personId));
        model.addAttribute("books", this.personDAO.getBooksByPersonId(personId));
        return "/people/show";
    }

    @GetMapping({"/new"})
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "/people/new";
        } else {
            this.personDAO.save(person);
            return "redirect:/people";
        }
    }

    @GetMapping({"/{personId}/edit"})
    public String edit(@PathVariable("personId") int personId, Model model) {
        model.addAttribute("person", this.personDAO.show(personId));
        return "people/edit";
    }

    @PatchMapping({"/{personId}"})
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult result, @PathVariable("personId") int personId) {
        if (result.hasErrors()) {
            return "people/edit";
        } else {
            this.personDAO.update(personId, person);
            return "redirect:/people";
        }
    }

    @DeleteMapping({"/{personId}"})
    public String delete(@PathVariable("personId") int personId) {
        this.personDAO.delete(personId);
        return "redirect:/people";
    }
}
