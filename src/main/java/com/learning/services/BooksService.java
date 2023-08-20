package com.learning.services;

import com.learning.models.Book;
import com.learning.models.Person;
import com.learning.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int bookId) {
        return booksRepository.findById(bookId).orElse(null);
    }

    @Transactional
    public void save(Book newBook) {
        booksRepository.save(newBook);
    }

    @Transactional
    public void update(int bookId, Book newBook) {
        newBook.setBookId(bookId);
        booksRepository.save(newBook);
    }

    @Transactional
    public void delete(int bookId) {
        booksRepository.deleteById(bookId);
    }

    @Transactional
    public void releaseBook(int bookId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        optionalBook.ifPresent(book -> book.setOwner(null));
    }

    @Transactional
    public void assignBook(int bookId, Person owner) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        optionalBook.ifPresent(book -> book.setOwner(owner));
    }

}
