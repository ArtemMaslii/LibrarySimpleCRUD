package com.learning.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int book_id;

    private int owner_id;

    @NotEmpty
    @Size(min = 1, max = 100, message = "Book name should be between 1 and 100 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50, message = "Author name/surname should be between 2 and 50 characters")
    private String author;

    @Min(value = 1600, message = "Book can be acceptable if it's not older than 423 years")
    private int year_of_publish;

    public Book() {
    }

    public Book(int book_id, int owner_id, String name, String author, int year_of_publish) {
        this.book_id = book_id;
        this.owner_id = owner_id;
        this.name = name;
        this.author = author;
        this.year_of_publish = year_of_publish;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
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

    public int getYear_of_publish() {
        return year_of_publish;
    }

    public void setYear_of_publish(int year_of_publish) {
        this.year_of_publish = year_of_publish;
    }
}
