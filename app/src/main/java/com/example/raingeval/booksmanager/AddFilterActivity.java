package com.example.raingeval.booksmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filter);
    }

    public void addFilter(View view) {
        EditText editAuthorFilter = (EditText) findViewById(R.id.edit_author_filter);
        String authorFilter = editAuthorFilter.getText().toString();

        EditText editCategoryFilter = (EditText) findViewById(R.id.edit_category_filter);
        String categoryFilter = editCategoryFilter.getText().toString();

        EditText editPublisherFilter = (EditText) findViewById(R.id.edit_publisher_filter);
        String publisherFilter = editPublisherFilter.getText().toString();

        EditText editYearFilter = (EditText) findViewById(R.id.edit_year_filter);
        String yearFilter = editYearFilter.getText().toString();
        

        BookFilterCatalog bookFilterCatalog = new BookFilterCatalog(this);
        BookFilter filter = bookFilterCatalog.createBookFilter(authorFilter,categoryFilter, publisherFilter, yearFilter);
        bookFilterCatalog.addBookFilter(filter);
        Toast toast = Toast.makeText(this, R.string.filter_added, Toast.LENGTH_SHORT);
        toast.show();
    }
}
