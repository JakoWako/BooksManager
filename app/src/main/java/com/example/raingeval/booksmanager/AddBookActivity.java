package com.example.raingeval.booksmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }

    public void addBook(View view) {
        EditText editAuthor = (EditText) findViewById(R.id.add_author);
        String author = editAuthor.getText().toString();

        EditText editTitle = (EditText) findViewById(R.id.add_title);
        String title = editTitle.getText().toString();

        EditText editIsbn = (EditText) findViewById(R.id.add_isbn);
        String isbn = editIsbn.getText().toString();

        EditText editCategory = (EditText) findViewById(R.id.add_category);
        String category = editCategory.getText().toString();

        EditText editPublisher = (EditText) findViewById(R.id.add_publisher);
        String publisher = editCategory.getText().toString();

        EditText editYear = (EditText) findViewById(R.id.add_year);
        String year = editCategory.getText().toString();

        EditText editDescription = (EditText) findViewById(R.id.add_description);
        String description = editCategory.getText().toString();

        BookLibrary bookLibrary = new BookLibrary(this);
        Book b = bookLibrary.createBook(author, title, isbn, category, publisher, year, null, description);
        bookLibrary.addBook(b);
        Toast toast = Toast.makeText(this, R.string.book_added, Toast.LENGTH_SHORT);
        toast.show();
    }


}
