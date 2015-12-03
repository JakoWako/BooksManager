package com.example.raingeval.booksmanager;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToAddBookChoiceActivity(View view) {
        Intent intent = new Intent(this, AddBookChoiceActivity.class);
        startActivity(intent);
    }

    public void goToFilterActivity(View view) {
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);

    }

}
