package com.example.c.t06_customlistview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    class MyData{
        String title, subTitle;
        int imgIcon;

        public MyData(String title, String subTitle, int imgIcon) {
            this.title = title;
            this.subTitle = subTitle;
            this.imgIcon = imgIcon;
        }
    }

    ArrayList<MyData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<MyData>();
        list.add(new MyData("Title1", "SubTitle1", R.mipmap.ic_launcher));
        list.add(new MyData("Title2", "SubTitle2", R.mipmap.ic_plain));
        list.add(new MyData("Title3", "SubTitle3", R.mipmap.ic_worldmap));
        list.add(new MyData("Title4", "SubTitle4", R.drawable.jojo));

        MyAdapter adapter = new MyAdapter(this, R.layout.item_row, list);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
