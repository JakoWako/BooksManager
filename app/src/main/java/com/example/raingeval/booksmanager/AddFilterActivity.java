package com.example.raingeval.booksmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
        

        BookFilterCatalog bookFilterCatalog = new BookFilterCatalog(this);
        BookFilter filter = bookFilterCatalog.createBookFilter(authorFilter,categoryFilter);
        bookFilterCatalog.addBookFilter(filter);
    }
}
