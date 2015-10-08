package com.example.raingeval.booksmanager;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raingeval on 24/09/15.
 */
public class BookLibrary {
    private static  List<Book> booksList = new ArrayList<>();

    public BookLibrary() {
    }

    public void addBook(Book book) {
        this.booksList.add(book);
    }

    public void deleteBook(Book book) {
        this.booksList.remove(book);
    }

    public Book createBook(){
        Book book = new Book();
        return book;
    }

    public Book createBook(String author, String title, String isbn, String category){
        Book book = new Book(author, title, isbn, category);
        return book;
    }

    public List<Book> getBooksList() {

        return booksList;
    }
}
