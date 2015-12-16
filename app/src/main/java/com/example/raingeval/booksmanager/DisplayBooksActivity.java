package com.example.raingeval.booksmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayBooksActivity extends AppCompatActivity {

    private BookLibrary bookLibrary;
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
            this.bookLibrary = new BookLibrary(this);
            booksToDisplay = this.bookLibrary.getBooksList();
        }


        List<Map<String, String>> listOfBooks = new ArrayList<Map<String, String>>();
        for (Book book : booksToDisplay) {
            Map<String, String> bookMap = new HashMap<String, String>();
            bookMap.put("id", String.valueOf(book.getId()));
            bookMap.put("img", book.getCoverPath());
            bookMap.put("author", book.getAuthor());
            bookMap.put("title", book.getTitle());
            bookMap.put("isbn", book.getIsbn());
            bookMap.put("category", book.getCategory());
            listOfBooks.add(bookMap);
        }
        SimpleAdapter listAdapter = new SimpleAdapter(this.getBaseContext(), listOfBooks, R.layout.book_detail, new String[] {"id","img","author","title","isbn","category"}, new int[] {R.id.id, R.id.img, R.id.author, R.id.title, R.id.isbn, R.id.category});
        booksList.setAdapter(listAdapter);


    }

    public void deleteBook(View view){
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent().getParent();
        final int position = listView.getPositionForView(parentRow);
        HashMap item = (HashMap) listView.getItemAtPosition(position);
        String idString = (String) item.get("id");
        final long id = Long.parseLong(idString);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete book");
        builder.setMessage("Do you really want to delete this book ?");

        final DisplayBooksActivity self = this; //pour pouvoir l'utiliser juste après
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                self.bookLibrary.deleteBook(id);
                self.recreate();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void editBook(View view){
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent().getParent();
        final int position = listView.getPositionForView(parentRow);
        HashMap item = (HashMap) listView.getItemAtPosition(position);
        String idString = (String) item.get("id");
        goToEditBookActivity(view, idString);

    }

    public void goToEditBookActivity(View view, String idString) {
        Intent intent = new Intent(this, EditBookActivity.class);
        intent.putExtra("EXTRA_MESSAGE", idString);
        startActivity(intent);
    }

}
