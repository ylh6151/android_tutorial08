package com.example.a.p02_menolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by a on 2015-04-16.
 */
public class MemoDBHandler {
    MemoSqliteOpenHelper helper;
    SQLiteDatabase db;

    public MemoDBHandler(Context context){
        helper = new MemoSqliteOpenHelper(context, "memo.sqlite", null, 1);
    }

    void insert(String memo, String date, String path){
        ContentValues values = new ContentValues();
        values.put("memo", memo);
        values.put("date", date);
        values.put("path", path);

        db = helper.getWritableDatabase();
        db.insert("memo", null, values);
    }

    public ArrayList<String> getAllMemoTitle(){
        db = helper.getReadableDatabase();
        ArrayList<String> memoTitleList = new ArrayList<String>();

        String sql = "select * from memo";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String str = cursor.getString( cursor.getColumnIndex("memo"));
            memoTitleList.add(str);
        }
        return memoTitleList;
    }
}
