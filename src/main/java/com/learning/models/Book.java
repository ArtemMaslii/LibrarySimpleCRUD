package com.learning.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int bookId;

    private int ownerId;

    @NotEmpty
    @Size(min = 1, max = 100, message = "Book name should be between 1 and 100 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50, message = "Author name/surname should be between 2 and 50 characters")
    private String author;

    @Min(value = 1600, message = "Book can be acceptable if it's not older than 423 years")
    private int yearOfPublish;

    public Book() {
    }

    public Book(int book_id, int owner_id, String name, String author, int yearOfPublish) {
        this.bookId = book_id;
        this.ownerId = owner_id;
        this.name = name;
        this.author = author;
        this.yearOfPublish = yearOfPublish;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }
}
