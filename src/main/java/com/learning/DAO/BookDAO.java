package com.learning.DAO;


import com.learning.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    public void save(Book newBook) {
        jdbcTemplate.update("INSERT INTO Book(person_id, book_name, author, year_of_book) VALUES (?, ?, ?, ?)",
                newBook.getOwnerId(), newBook.getName(), newBook.getAuthor(), newBook.getYearOfPublish());
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET person_id=?, book_name=?, author=?, year_of_book=? WHERE id=?",
                updatedBook.getOwnerId(), updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYearOfPublish(), bookId);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
