package com.example.raingeval.booksmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayFiltersActivity extends AppCompatActivity {

    private static List<Book> filteredBooks = new ArrayList<Book>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_filters);

        ListView filtersList = (ListView) findViewById(R.id.filterslist);

        BookFilterCatalog bookFilterCatalog = new BookFilterCatalog(this);

        List<Map<String, String>> listOfFilters = new ArrayList<Map<String, String>>();
        for (BookFilter filter : bookFilterCatalog.getBookFiltersList()) {
            Map<String, String> filterMap = new HashMap<String, String>();
            filterMap.put("author", "Author : " + filter.getAuthorFilter());
            filterMap.put("publisher", "Publisher : " + filter.getPublisherFilter());
            filterMap.put("category", "Category : " + filter.getCategoryFilter());
            filterMap.put("year", "Year : " + filter.getYearFilter());
            listOfFilters.add(filterMap);
        }

        SimpleAdapter listAdapter = new SimpleAdapter(this.getBaseContext(), listOfFilters, R.layout.filter_detail, new String[] {"author","category","publisher","year"}, new int[] {R.id.author_filter, R.id.category_filter, R.id.publisher_filter, R.id.year_filter});
        filtersList.setAdapter(listAdapter);
        final Context context = this; //binding du contexte
        filtersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                filteredBooks.clear();
                HashMap item = (HashMap) parent.getItemAtPosition(position);
                String author = (String) item.get("author");
                author = author.replace("Author : ","");
                String category = (String) item.get("category");
                category = category.replace("Category : ","");
                String publisher = (String) item.get("publisher");
                publisher = publisher.replace("Publisher : ","");
                String year = (String) item.get("year");
                year = year.replace("Year : ","");
                BookFilter filter = new BookFilter(author,category, publisher, year);
                BookLibrary library = new BookLibrary(context);
                //filteredBooks = library.getBooksFiltered(filter);
                for (Book b : library.getBooksList()){
                    if (filter.isSelected(b)){
                        filteredBooks.add(b);
                    }
                }
                goToDisplayBooksActivity(view);
            }
        });

    }

    public List<Book> getFilteredBooksList() {

        return filteredBooks;
    }

    public void goToDisplayBooksActivity(View view) {
        Intent intent = new Intent(this, DisplayBooksActivity.class);
        intent.putExtra("EXTRA_MESSAGE", "filter");
        startActivity(intent);
    }

}
