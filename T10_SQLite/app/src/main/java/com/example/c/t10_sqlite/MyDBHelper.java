package com.example.c.t10_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by c on 2015-07-26.
 */
public class MyDBHelper {

    T10SQLiteOpenHelper helper;
    SQLiteDatabase db;

    public MyDBHelper(Context context) {
        helper = new T10SQLiteOpenHelper(context,"person.sqlite", null, 1);
    }

    public void insert(String name, int age, String address){
        db = helper.getWritableDatabase();
        //String str = "INSERT INTO student VALUES(null," + name + ","+age+","+address+")";

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("address", address);

        db.insert("student", null, values);
    }

    public void updateAge(String name, int newAge){
        ContentValues values = new ContentValues();
        values.put("age", newAge);

        db = helper.getWritableDatabase();
        db.update("student", values, "name = ?", new String[]{name});
    }

    public void delete(String name){
        db = helper.getWritableDatabase();
        db.delete("student", "name = ?", new String[]{name});
    }

    public String getStudentData(){
        db = helper.getReadableDatabase();
        //Cursor c = db.query("student",null,null,null,null,null,null);

        String str = "SELECT * FROM student";
        Cursor c = db.rawQuery(str, null);

        String result = "";
        while(c.moveToNext()){
            String name = c.getString(c. getColumnIndex("name"));
            int age = c.getInt(c. getColumnIndex("age"));
            String address = c.getString(c. getColumnIndex("address"));

            result += "name :" + name + ", age :"+ age +", address : "+address + "\n";
        }
        return result;
    }
}
