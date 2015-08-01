package com.example.c.p01_mediaplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by c on 2015-08-01.
 */
public class MediaPlayerSQLiteOpenHelper extends SQLiteOpenHelper {
    public MediaPlayerSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE playlist (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "file_name TEXT, stop_position INTEGER, play_date DATE)";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String str = "DROP TABLE IF EXIST playlist";
        db.execSQL(str);

        onCreate(db);
    }
}
