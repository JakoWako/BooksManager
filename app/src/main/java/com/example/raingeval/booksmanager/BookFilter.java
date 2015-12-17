package com.example.raingeval.booksmanager;

/**
 * Created by Anthony on 07/10/2015.
 */
public class BookFilter {

    private String authorFilter;
    private String categoryFilter;
    private String publisherFilter;
    private String yearFilter;

    BookFilter(){
        this.authorFilter = "";
        this.categoryFilter = "";
        this.publisherFilter = "";
        this.yearFilter = "";
    }

    BookFilter(String authorFilter, String categoryFilter, String publisherFilter, String yearFilter){
        this.authorFilter = authorFilter;
        this.categoryFilter = categoryFilter;
        this.publisherFilter = publisherFilter;
        this.yearFilter = yearFilter;
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

    public String getPublisherFilter() {
        return publisherFilter;
    }

    public void setPublisherFilter(String publisherFilter) {
        this.publisherFilter = publisherFilter;
    }

    public String getYearFilter() {
        return yearFilter;
    }

    public void setYearFilter(String yearFilter) {
        this.yearFilter = yearFilter;
    }

    Boolean isSelected(Book book){
        Boolean authorOk;
        Boolean categoryOk;
        Boolean publisherOk;
        Boolean yearOk;
        authorOk = (book.getAuthor().equals(this.authorFilter) || this.authorFilter.equals(""));
        categoryOk = (book.getCategory().equals(this.categoryFilter) || this.categoryFilter.equals(""));
        publisherOk = (book.getPublisher().equals(this.publisherFilter) || this.publisherFilter.equals(""));
        yearOk = (book.getYear().equals(this.yearFilter) || this.yearFilter.equals(""));

        return authorOk && categoryOk && publisherOk && yearOk;
    }
}
