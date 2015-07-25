package com.example.c.t02_menu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView)findViewById(R.id.myTextView);
        registerForContextMenu(myTextView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("My Context Menu");
        menu.add(0, 1, 0, "My Context Item 1");
        menu.add(0, 2, 0, "My Context Item 2");
        menu.add(0, 3, 0, "My Context Item 3");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(this,"Context menu 1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"Context menu 2", Toast.LENGTH_SHORT).show();
                break;
            case 31:
                Toast.makeText(this,"Context menu 3", Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onContextItemSelected(item);
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
            Log.d("Menu", "menu 1");
            Toast.makeText(this,"menu 1", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_settings2) {
            Log.d("Menu", "menu 2");
            Toast.makeText(this,"menu 2", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_settings3) {
            Log.d("Menu", "menu 3");
            Toast.makeText(this,"menu 3", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
