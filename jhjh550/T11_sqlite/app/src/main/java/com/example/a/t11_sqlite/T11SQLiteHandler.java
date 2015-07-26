package com.example.a.t11_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a on 2015-04-16.
 */
public class T11SQLiteHandler {
    T11SQLOpenHelper helper;
    SQLiteDatabase db;

    public T11SQLiteHandler(Context context){
        helper = new T11SQLOpenHelper(context, "person.sqlite", null, 1);
    }

    public void insert(String name, int age, String address){
        db = helper.getWritableDatabase();

        //String sql = "insert into student values(null,"+name+","+age+","+address+")";
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("address", address);

        db.insert("student", null, values);
    }

    public void updateAge(String name, int newAge){
        ContentValues updateValues = new ContentValues();
        updateValues.put("age", newAge);

        db = helper.getWritableDatabase();
        db.update("student", updateValues, "name = ?", new String[]{name});
    }

    public void delete(String name){
        db = helper.getWritableDatabase();
        db.delete("student", "name = ?", new String[]{name});
    }

    public String getStudentData(){
        db = helper.getReadableDatabase();

        //Cursor c = db.query("student", null, null, null, null,null, null);
        String sql = "select * from student";
        Cursor c = db.rawQuery(sql, null);

        String result = "";
        while (c.moveToNext()){
            int id = c.getInt( c.getColumnIndex("id") );
            String name = c.getString( c.getColumnIndex("name") );
            int age = c.getInt( c.getColumnIndex("age") );
            String address = c.getString( c.getColumnIndex("address"));

            result += "id :"+id+" name : "+name+" age : "+age+ "address : "+address+"\n";
        }

        return  result;
    }
}









