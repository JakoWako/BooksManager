package com.example.raingeval.booksmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }

    public void goToDisplayBooksActivity(View view) {
        Intent intent = new Intent(this, DisplayBooksActivity.class);
        intent.putExtra("EXTRA_MESSAGE", "");
        startActivity(intent);

    }

    public void goToAddFilterActivity(View view) {
        Intent intent = new Intent(this, AddFilterActivity.class);
        startActivity(intent);
    }

    public void goToDisplayFiltersActivity(View view) {
        Intent intent = new Intent(this, DisplayFiltersActivity.class);
        startActivity(intent);
    }
}
