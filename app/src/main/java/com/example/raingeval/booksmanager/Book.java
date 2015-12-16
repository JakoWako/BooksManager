package com.example.raingeval.booksmanager;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by raingeval on 24/09/15.
 */
public class Book {
    private long id;
    private String author;
    private String title;
    private String isbn;
    private String category;
    private String collection;
    private String publisher;
    private String year;
    private String coverPath;
    private String description;

    public Book() {
        this.author = "";
        this.title = "";
        this.isbn = "";
        this.category = "";
        this.coverPath = "";
    }

    public Book(String author, String title, String isbn, String category, String publisher, String year, String coverPath, String description) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.category = category;
        this.publisher = publisher;
        this.year = year;
        this.coverPath = coverPath;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getIsbn() {

        return isbn;
    }

    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}