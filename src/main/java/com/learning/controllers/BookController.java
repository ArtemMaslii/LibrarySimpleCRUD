package com.learning.controllers;

import com.learning.models.Book;
import com.learning.models.Person;
import com.learning.services.BooksService;
import com.learning.services.PeopleService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/books"})
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {
        if (page == null && booksPerPage == null) {
            model.addAttribute("bookList", this.booksService.findAll(sortByYear));
        } else {
            model.addAttribute("bookList", this.booksService.findWithPagination(page, booksPerPage, sortByYear));
        }

        return "/books/index";
    }

    @GetMapping({"/{bookId}"})
    public String show(@PathVariable("bookId") int bookId, Model model) {
        model.addAttribute("book", booksService.findById(bookId));
        Person owner = booksService.getBookOwner(bookId);

        if (owner != null) {
            model.addAttribute("owner", owner);
        } else {
            model.addAttribute("people", this.peopleService.findAll());
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
            this.booksService.save(book);
            return "redirect:/books";
        }
    }

    @GetMapping({"/{bookId}/edit"})
    public String edit(@PathVariable("bookId") int bookId, Model model) {
        model.addAttribute("book", this.booksService.findById(bookId));
        return "books/edit";
    }

    @PatchMapping({"/{bookId}"})
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult result, @PathVariable("bookId") int bookId) {
        if (result.hasErrors()) {
            return "books/edit";
        } else {
            this.booksService.update(bookId, book);
            return "redirect:/books";
        }
    }

    @DeleteMapping({"/{bookId}"})
    public String delete(@PathVariable("bookId") int bookId) {
        this.booksService.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping({"/{bookId}/release"})
    public String release(@PathVariable("bookId") int bookId) {
        this.booksService.releaseBook(bookId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping({"/{bookId}/assign"})
    public String assign(@PathVariable("bookId") int bookId, @RequestParam int personId) {
        Person customer = this.peopleService.findById(personId);
        if (customer == null) {
            return "redirect:/books";
        } else {
            this.booksService.assignBook(bookId, customer);
            return "redirect:/books/" + bookId;
        }
    }

    @GetMapping("/search")
    public String searchPage() {
        return "books/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("title") String title) {
        model.addAttribute("books", booksService.search(title));
        return "books/search";
    }
}
