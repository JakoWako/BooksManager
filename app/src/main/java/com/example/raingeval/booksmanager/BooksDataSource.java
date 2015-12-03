package com.example.raingeval.booksmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 01/11/2015.
 */
public class BooksDataSource {

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;
    private String[] allColumns = { MySQLiteOpenHelper.COLUMN_ID,
            MySQLiteOpenHelper.COLUMN_ISBN, MySQLiteOpenHelper.COLUMN_TITLE,
            MySQLiteOpenHelper.COLUMN_AUTHOR, MySQLiteOpenHelper.COLUMN_CATEGORY, MySQLiteOpenHelper.COLUMN_COVERPATH };

    public BooksDataSource(Context context){
        dbHelper = new MySQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public long insertBook(Book book){
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.COLUMN_ISBN, book.getIsbn());
        values.put(MySQLiteOpenHelper.COLUMN_TITLE, book.getTitle());
        values.put(MySQLiteOpenHelper.COLUMN_AUTHOR, book.getAuthor());
        values.put(MySQLiteOpenHelper.COLUMN_CATEGORY, book.getCategory());
        values.put(MySQLiteOpenHelper.COLUMN_COVERPATH, book.getCoverPath());
        return database.insert(MySQLiteOpenHelper.TABLE_BOOKS, null, values);
    }

    public long updateBook(int id, Book book){
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.COLUMN_ISBN, book.getIsbn());
        values.put(MySQLiteOpenHelper.COLUMN_TITLE, book.getAuthor());
        values.put(MySQLiteOpenHelper.COLUMN_AUTHOR, book.getCategory());
        values.put(MySQLiteOpenHelper.COLUMN_CATEGORY, book.getTitle());
        values.put(MySQLiteOpenHelper.COLUMN_COVERPATH, book.getCoverPath());
        return database.update(MySQLiteOpenHelper.TABLE_BOOKS, values, MySQLiteOpenHelper.COLUMN_ID + " = " + id, null);
    }

    public int removeBookWithID(int id){
        return database.delete(MySQLiteOpenHelper.TABLE_BOOKS, MySQLiteOpenHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();

        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_BOOKS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return books;
    }

    public List<Book> getBooksFiltered(BookFilter filter){
        List<Book> books = new ArrayList<Book>();
        String whereClause = MySQLiteOpenHelper.COLUMN_AUTHOR + " =? OR " + MySQLiteOpenHelper.COLUMN_CATEGORY + " =?";
        String[] whereArgs = new String[]{filter.getAuthorFilter(), filter.getCategoryFilter()};
        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_BOOKS,
                allColumns, whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        return books;
    }

    private Book cursorToBook(Cursor cursor) {
        Book book = new Book();
        //book.setId(cursor.getLong(0));
        book.setIsbn(cursor.getString(1));
        book.setTitle(cursor.getString(2));
        book.setAuthor(cursor.getString(3));
        book.setCategory(cursor.getString(4));
        book.setCoverPath(cursor.getString(5));
        return book;
    }
}
