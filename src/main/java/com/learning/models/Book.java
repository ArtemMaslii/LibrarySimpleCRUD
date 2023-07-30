package com.learning.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int bookId;
    private @NotEmpty @Size(min = 1, max = 100, message = "Book name should be between 1 and 100 characters")
    String name;
    private @NotEmpty @Size(min = 2, max = 100, message = "Author name/surname should be between 2 and 50 characters")
    String author;
    private @Min(value = 1000L, message = "Book can be acceptable if it's not older than 1023 years")
    int yearOfPublish;

    public Book() {
    }

    public Book(int book_id, String name, String author, int yearOfPublish) {
        this.bookId = book_id;
        this.name = name;
        this.author = author;
        this.yearOfPublish = yearOfPublish;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublish() {
        return this.yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }
}
