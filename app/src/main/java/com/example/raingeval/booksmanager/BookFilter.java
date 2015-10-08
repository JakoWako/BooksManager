package com.example.raingeval.booksmanager;

/**
 * Created by Anthony on 07/10/2015.
 */
public class BookFilter {

    private String authorFilter;
    private String categoryFilter;

    BookFilter(){
        this.authorFilter = "";
        this.categoryFilter = "";
    }

    BookFilter(String authorFilter, String categoryFilter){
        this.authorFilter = authorFilter;
        this.categoryFilter = categoryFilter;
    }

    public String getAuthorFilter(){
        return this.authorFilter;
    }

    public void setAuthorFilter(String authorFilter){
        this.authorFilter = authorFilter;
    }

    public String getCategoryFilter(){
        return this.categoryFilter;
    }

    public void setCategoryFilter(String categoryFilter){
        this.categoryFilter = categoryFilter;
    }

    Boolean isSelected(Book book){
        Boolean authorOk;
        Boolean categoryOk;
        authorOk = (book.getAuthor().equals(this.authorFilter) || this.authorFilter.equals(""));
        categoryOk = (book.getCategory().equals(this.categoryFilter) || this.categoryFilter.equals(""));

        return authorOk && categoryOk;
    }
}
