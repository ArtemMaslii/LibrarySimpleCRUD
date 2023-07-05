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

    public Book show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book newBook) {
        jdbcTemplate.update("INSERT INTO Book(personId, name, author, yearOfPublish) VALUES (?, ?, ?, ?)",
                newBook.getOwnerId(), newBook.getName(), newBook.getAuthor(), newBook.getYearOfPublish());
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET personId=?, name=?, author=?, yearOfPublish=? WHERE bookId=?",
                updatedBook.getOwnerId(), updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYearOfPublish(), bookId);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE bookId=?", id);
    }
}
