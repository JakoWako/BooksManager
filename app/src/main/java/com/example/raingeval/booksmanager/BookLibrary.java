package com.example.raingeval.booksmanager;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
//autre tst de branche
/**
 * Created by raingeval on 24/09/15.
 */
public class BookLibrary {
    private static  List<Book> booksList = new ArrayList<>();
    private BooksDataSource booksDataSource;

    public BookLibrary(Context context) {
        this.booksDataSource = new BooksDataSource(context);
    }

    public void addBook(Book book) {

        try{
            this.booksDataSource.open();
        }catch(java.sql.SQLException e){

        };
        this.booksDataSource.insertBook(book);
        this.booksDataSource.close();
        //this.booksList.add(book);
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

        try{
            this.booksDataSource.open();
        }catch(java.sql.SQLException e){

        };
        List<Book> books = this.booksDataSource.getAllBooks();
        this.booksDataSource.close();
        return books;
    }

    public List<Book> getBooksFiltered(BookFilter filter){
        try{
            this.booksDataSource.open();
        }catch(java.sql.SQLException e){

        };
        List<Book> books = this.booksDataSource.getBooksFiltered(filter);
        this.booksDataSource.close();
        return books;
    }
}
