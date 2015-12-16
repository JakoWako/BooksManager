package com.example.raingeval.booksmanager;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
//autre tst de branche
/**
 * Created by raingeval on 24/09/15.
 */
public class BookLibrary {
    private BooksDataSource booksDataSource;

    public BookLibrary(Context context) {
        this.booksDataSource = new BooksDataSource(context);
    }

    public void addBook(Book book) {

        try{
            this.booksDataSource.open();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        this.booksDataSource.insertBook(book);
        this.booksDataSource.close();
    }

    public void deleteBook(long id) {
        try{
            this.booksDataSource.open();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        this.booksDataSource.removeBookWithID(id);
        this.booksDataSource.close();
    }

    public Book createBook(){
        Book book = new Book();
        return book;
    }

    public void editBook(Book book, long id){
        try{
            this.booksDataSource.open();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        this.booksDataSource.updateBook(id, book);
        this.booksDataSource.close();
    }

    public Book createBook(String author, String title, String isbn, String category, String publisher, String year, String imagePath, String description){

        Book book = new Book(author, title, isbn, category, publisher, year, imagePath, description);
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

        }
        List<Book> books = this.booksDataSource.getBooksFiltered(filter);
        this.booksDataSource.close();
        return books;
    }

    public Book getBookById(long id){
        try{
            this.booksDataSource.open();
        }catch(java.sql.SQLException e){

        }
        Book book = this.booksDataSource.getBookByID(id);
        this.booksDataSource.close();
        return book;
    }
}
