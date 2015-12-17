package com.example.raingeval.booksmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;

public class DetailActivity extends AppCompatActivity {
    private long bookId;
    private Book book;
    private TextView textAuthor, textTitle, textIsbn, textCategory, textPublisher, textYear, textDescription;
    private ImageView coverView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        this.bookId = Long.parseLong(intent.getStringExtra("EXTRA_MESSAGE"));
        BookLibrary bookLibrary = new BookLibrary(this);
        book = bookLibrary.getBookById(bookId);

        textAuthor = (TextView) findViewById(R.id.author_detail);
        textAuthor.setText(book.getAuthor());

        textTitle = (TextView) findViewById(R.id.title_detail);
        textAuthor.setText(book.getTitle());

        textIsbn = (TextView) findViewById(R.id.isbn_detail);
        textIsbn.setText(book.getIsbn());

        textCategory = (TextView) findViewById(R.id.category_detail);
        textCategory.setText(book.getCategory());

        textPublisher = (TextView) findViewById(R.id.publisher_detail);
        textPublisher.setText(book.getPublisher());

        textYear = (TextView) findViewById(R.id.year_detail);
        textYear.setText(book.getYear());

        textDescription = (TextView) findViewById(R.id.description_detail);
        textDescription.setText(book.getDescription());

        coverView = (ImageView) findViewById(R.id.img_detail);


        Bitmap myBitmap = BitmapFactory.decodeFile(book.getCoverPath());

        coverView.setImageBitmap(myBitmap);
    }
}
