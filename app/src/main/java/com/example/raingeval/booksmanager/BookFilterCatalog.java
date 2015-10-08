package com.example.raingeval.booksmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 07/10/2015.
 */
public class BookFilterCatalog {
    private static List<BookFilter> bookFiltersList = new ArrayList<>();

    public BookFilterCatalog(){

    }

    public void addBookFilter(BookFilter bookFilter) {
        this.bookFiltersList.add(bookFilter);
    }

    public void deleteBookFilter(BookFilter bookFilter) {
        this.bookFiltersList.remove(bookFilter);
    }

    public BookFilter createBookFilter(){
        BookFilter bookFilter = new BookFilter();
        return bookFilter;
    }

    public BookFilter createBookFilter(String authorFilter, String categoryFilter){
        BookFilter bookFilter = new BookFilter(authorFilter, categoryFilter);
        return bookFilter;
    }

    public List<BookFilter> getBookFiltersList() {

        return bookFiltersList;
    }
}
