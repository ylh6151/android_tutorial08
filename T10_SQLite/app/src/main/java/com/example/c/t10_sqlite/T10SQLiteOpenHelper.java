package com.example.c.t10_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by c on 2015-07-26.
 */
public class T10SQLiteOpenHelper extends SQLiteOpenHelper {
    public T10SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, age INTEGER, address TEXT)";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String str = "DROP TABLE IF EXIST student";
        db.execSQL(str);

        onCreate(db);
    }
}
