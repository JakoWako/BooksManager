package com.example.raingeval.booksmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }

    public void addBook(View view) {
        EditText editAuthor = (EditText) findViewById(R.id.edit_author);
        String author = editAuthor.getText().toString();

        EditText editTitle = (EditText) findViewById(R.id.edit_title);
        String title = editTitle.getText().toString();

        EditText editIsbn = (EditText) findViewById(R.id.edit_isbn);
        String isbn = editIsbn.getText().toString();

        EditText editCategory = (EditText) findViewById(R.id.edit_category);
        String category = editCategory.getText().toString();

        BookLibrary bookLibrary = new BookLibrary();
        Book b = bookLibrary.createBook(author, title, isbn, category);
        bookLibrary.addBook(b);
    }


}
