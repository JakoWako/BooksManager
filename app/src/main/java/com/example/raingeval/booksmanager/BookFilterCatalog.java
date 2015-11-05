package com.example.raingeval.booksmanager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 07/10/2015.
 */
public class BookFilterCatalog {
    private static List<BookFilter> bookFiltersList = new ArrayList<>();
    private FiltersDataSource filtersDataSource;

    public BookFilterCatalog(Context context){
        this.filtersDataSource = new FiltersDataSource(context);
    }

    public void addBookFilter(BookFilter bookFilter) {

        try{
            this.filtersDataSource.open();
        }catch(java.sql.SQLException e){

        };
        this.filtersDataSource.insertFilter(bookFilter);
        this.filtersDataSource.close();

        //this.bookFiltersList.add(bookFilter);
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

        try{
            this.filtersDataSource.open();
        }catch(java.sql.SQLException e){

        };
        List<BookFilter> filters = this.filtersDataSource.getAllFilters();
        this.filtersDataSource.close();
        return filters;
    }
}
