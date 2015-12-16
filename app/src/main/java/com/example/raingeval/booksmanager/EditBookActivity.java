package com.example.raingeval.booksmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditBookActivity extends AppCompatActivity {

    private long bookId;
    private Book book;
    private EditText editAuthor, editTitle, editIsbn, editCategory, editPublisher, editYear, editDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        Intent intent = getIntent();
        this.bookId = Long.parseLong(intent.getStringExtra("EXTRA_MESSAGE"));
        BookLibrary bookLibrary = new BookLibrary(this);
        book = bookLibrary.getBookById(bookId);

        editAuthor = (EditText) findViewById(R.id.edit_author);
        editAuthor.setText(book.getAuthor());

        editTitle = (EditText) findViewById(R.id.edit_title);
        editTitle.setText(book.getTitle());

        editIsbn = (EditText) findViewById(R.id.edit_isbn);
        editIsbn.setText(book.getIsbn());

        editCategory = (EditText) findViewById(R.id.edit_category);
        editCategory.setText(book.getCategory());

        editPublisher = (EditText) findViewById(R.id.edit_publisher);
        editPublisher.setText(book.getPublisher());

        editYear = (EditText) findViewById(R.id.edit_year);
        editYear.setText(book.getYear());

        editDescription = (EditText) findViewById(R.id.edit_description);
        editDescription.setText(book.getDescription());
    }

    public void editBook(View view) {

        String author = editAuthor.getText().toString();
        String title = editTitle.getText().toString();
        String isbn = editIsbn.getText().toString();
        String category = editCategory.getText().toString();
        String publisher = editPublisher.getText().toString();
        String year = editYear.getText().toString();
        String description = editDescription.getText().toString();

        BookLibrary bookLibrary = new BookLibrary(this);
        Book b = bookLibrary.createBook(author, title, isbn, category, publisher, year, book.getCoverPath(), description);
        bookLibrary.editBook(b, this.bookId);
        Toast toast = Toast.makeText(this, R.string.book_edited, Toast.LENGTH_SHORT);
        toast.show();
        this.finish();
    }
}
