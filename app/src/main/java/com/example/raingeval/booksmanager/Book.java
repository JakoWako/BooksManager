package com.example.raingeval.booksmanager;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by raingeval on 24/09/15.
 */
public class Book {
    private String author;
    private String title;
    private String isbn;
    private String category;


    private String coverPath;

    public Book() {
        this.author = "";
        this.title = "";
        this.isbn = "";
        this.category = "";
        this.coverPath = "";
    }

    public Book(String author, String title, String isbn, String category, String coverPath) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.category = category;
        this.coverPath = coverPath;
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
}