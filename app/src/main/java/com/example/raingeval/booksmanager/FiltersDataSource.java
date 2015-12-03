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
public class FiltersDataSource {

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;
    private String[] allColumns = { MySQLiteOpenHelper.COLUMN_ID,
            MySQLiteOpenHelper.COLUMN_AUTHOR, MySQLiteOpenHelper.COLUMN_CATEGORY };

    public FiltersDataSource(Context context){
        dbHelper = new MySQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public long insertFilter(BookFilter filter){
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.COLUMN_AUTHOR, filter.getAuthorFilter());
        values.put(MySQLiteOpenHelper.COLUMN_CATEGORY, filter.getCategoryFilter());
        return database.insert(MySQLiteOpenHelper.TABLE_FILTERS, null, values);
    }

    public long updateFilter(int id, BookFilter filter){
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.COLUMN_TITLE, filter.getAuthorFilter());
        values.put(MySQLiteOpenHelper.COLUMN_CATEGORY, filter.getCategoryFilter());
        return database.update(MySQLiteOpenHelper.TABLE_FILTERS, values, MySQLiteOpenHelper.COLUMN_ID + " = " + id, null);
    }

    public int removeFilterWithID(int id){
        return database.delete(MySQLiteOpenHelper.TABLE_FILTERS, MySQLiteOpenHelper.COLUMN_ID + " = " + id, null);
    }

    public List<BookFilter> getAllFilters() {
        List<BookFilter> filters = new ArrayList<BookFilter>();

        Cursor cursor = database.query(MySQLiteOpenHelper.TABLE_FILTERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BookFilter filter = cursorToFilter(cursor);
            filters.add(filter);
            cursor.moveToNext();
        }
        cursor.close();
        return filters;
    }

    private BookFilter cursorToFilter(Cursor cursor) {
        BookFilter filter = new BookFilter();
        //book.setId(cursor.getLong(0));
        filter.setAuthorFilter(cursor.getString(1));
        filter.setCategoryFilter(cursor.getString(2));
        return filter;
    }
}
