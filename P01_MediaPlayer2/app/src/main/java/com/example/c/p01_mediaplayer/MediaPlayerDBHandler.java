package com.example.c.p01_mediaplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by c on 2015-08-01.
 */
public class MediaPlayerDBHandler {

    MediaPlayerSQLiteOpenHelper helper;
    SQLiteDatabase db;

    public MediaPlayerDBHandler(Context context) {
        helper = new MediaPlayerSQLiteOpenHelper(context,"playhistory.sqlite", null, 1);
    }

    public void insert(String name, int age, String date){
        db = helper.getWritableDatabase();
        //String str = "INSERT INTO student VALUES(null," + name + ","+age+","+address+")";

        ContentValues values = new ContentValues();
        values.put("file_name", name);
        values.put("stop_position", age);
        values.put("play_date", date);

        db.insert("playlist", null, values);
    }

    public void updateStopPosition(String filename, int stopPosition){
        ContentValues values = new ContentValues();
        values.put("stop_position", stopPosition);

        db = helper.getWritableDatabase();
        db.update("playlist", values, "file_name = ?", new String[]{filename});
    }

    public void delete(String filename){
        db = helper.getWritableDatabase();
        db.delete("playlist", "file_name = ?", new String[]{filename});
    }

    public String getPlayListData(){
        db = helper.getReadableDatabase();
        //Cursor c = db.query("student",null,null,null,null,null,null);

        String str = "SELECT * FROM playlist";
        Cursor c = db.rawQuery(str, null);

        String result = "";
        while(c.moveToNext()){
            String filename = c.getString(c. getColumnIndex("file_name"));
            int age = c.getInt(c. getColumnIndex("stop_position"));
            String address = c.getString(c. getColumnIndex("play_date"));

            result += "name :" + filename + ", age :"+ age +", address : "+address + "\n";
        }
        return result;
    }
}
