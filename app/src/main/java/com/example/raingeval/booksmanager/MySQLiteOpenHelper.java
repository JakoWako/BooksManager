package com.example.raingeval.booksmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Anthony on 01/11/2015.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_FILTERS = "filters";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ISBN = "isbn";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_PUBLISHER = "publisher";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_COVERPATH = "coverPath";
    public static final String COLUMN_DESCRIPTION = "description";

    private static final String DATABASE_NAME = "books.db";
    private static final int DATABASE_VERSION = 1;

    // commande SQL pour création de la base
    private static final String DATABASE_CREATE = "create table "
            + TABLE_BOOKS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_ISBN
            + " text not null, " + COLUMN_TITLE
            + " text not null, " + COLUMN_AUTHOR
            + " text not null, " + COLUMN_CATEGORY
            + ", " + COLUMN_PUBLISHER
            + ", " + COLUMN_YEAR
            + ", " + COLUMN_COVERPATH
            + ", " + COLUMN_DESCRIPTION + ");";

    private static final String TABLE_CREATE = "create table "
            + TABLE_FILTERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_AUTHOR + ", " + COLUMN_CATEGORY + ", " + COLUMN_PUBLISHER + ", " + COLUMN_YEAR + ");";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
        database.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(MySQLiteOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILTERS);
        onCreate(db);
    }

}
