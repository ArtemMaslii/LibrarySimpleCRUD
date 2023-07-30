package com.learning.controllers;

import com.learning.DAO.BookDAO;
import com.learning.DAO.PersonDAO;
import com.learning.models.Book;
import com.learning.models.Person;
import jakarta.validation.Valid;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/books"})
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("bookList", this.bookDAO.index());
        return "/books/index";
    }

    @GetMapping({"/{bookId}"})
    public String show(@PathVariable("bookId") int bookId, Model model) {
        model.addAttribute("book", this.bookDAO.show(bookId));
        Optional<Person> owner = this.bookDAO.getBookOwner(bookId);
        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("people", this.personDAO.index());
        }

        return "books/show";
    }

    @GetMapping({"/new"})
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/new";
        } else {
            this.bookDAO.save(book);
            return "redirect:/books";
        }
    }

    @GetMapping({"/{bookId}/edit"})
    public String edit(@PathVariable("bookId") int bookId, Model model) {
        model.addAttribute("book", this.bookDAO.show(bookId));
        return "books/edit";
    }

    @PatchMapping({"/{bookId}"})
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult result, @PathVariable("bookId") int bookId) {
        if (result.hasErrors()) {
            return "books/edit";
        } else {
            this.bookDAO.update(bookId, book);
            return "redirect:/books";
        }
    }

    @DeleteMapping({"/{bookId}"})
    public String delete(@PathVariable("bookId") int bookId) {
        this.bookDAO.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping({"/{bookId}/release"})
    public String release(@PathVariable("bookId") int bookId) {
        this.bookDAO.releaseBook(bookId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping({"/{bookId}/assign"})
    public String assign(@PathVariable("bookId") int bookId, @RequestParam int personId) {
        Person customer = this.personDAO.show(personId);
        if (customer == null) {
            return "redirect:/books";
        } else {
            this.bookDAO.assignBook(bookId, customer);
            return "redirect:/books/" + bookId;
        }
    }
}
