package com.example.raingeval.booksmanager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BookLibrary bookLibrary = new BookLibrary();
        Book b1 = new Book("Popo", "La grande aventure", "01", "Aventure");
        Book b2 = new Book("Popo", "La grande romance", "02", "Romance");
        Book b3 = new Book("Jiji", "La petite aventure", "03", "Aventure");
        Book b4 = new Book("Jiji", "L'enquête de Jiji", "04", "Policier");
        bookLibrary.addBook(b1);
        bookLibrary.addBook(b2);
        bookLibrary.addBook(b3);
        bookLibrary.addBook(b4);
        BookFilterCatalog filterCatalog = new BookFilterCatalog();
        BookFilter f1 = new BookFilter("Jiji","");
        BookFilter f2 = new BookFilter("","Aventure");
        filterCatalog.addBookFilter(f1);
        filterCatalog.addBookFilter(f2);
    }

    public void goToAddBookActivity(View view) {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    public void goToFilterActivity(View view) {
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);

    }

}
