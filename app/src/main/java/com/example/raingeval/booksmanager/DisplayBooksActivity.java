package com.example.raingeval.booksmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);

        ListView booksList = (ListView) findViewById(R.id.bookslist);

        Intent intent = getIntent();
        List<Book> booksToDisplay;
        if (intent.getStringExtra("EXTRA_MESSAGE").equals("filter")){
            DisplayFiltersActivity  displayFilters = new DisplayFiltersActivity();
            booksToDisplay = displayFilters.getFilteredBooksList();
        }else{
            BookLibrary bookLibrary = new BookLibrary(this);
            booksToDisplay = bookLibrary.getBooksList();
        }


        List<Map<String, String>> listOfBooks = new ArrayList<Map<String, String>>();
        for (Book book : booksToDisplay) {
            Map<String, String> bookMap = new HashMap<String, String>();
            bookMap.put("img", String.valueOf(R.drawable.mouches));
            bookMap.put("author", book.getAuthor());
            bookMap.put("title", book.getTitle());
            bookMap.put("isbn", book.getIsbn());
            bookMap.put("category", book.getCategory());
            listOfBooks.add(bookMap);
        }

        SimpleAdapter listAdapter = new SimpleAdapter(this.getBaseContext(), listOfBooks, R.layout.book_detail, new String[] {"img","author","title","isbn","category"}, new int[] {R.id.img, R.id.author, R.id.title, R.id.isbn, R.id.category});
        booksList.setAdapter(listAdapter);


    }

}
