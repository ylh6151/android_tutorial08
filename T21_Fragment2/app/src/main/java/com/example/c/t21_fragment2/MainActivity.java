package com.example.c.t21_fragment2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMyClick(View v){
        FragmentManager fm = getFragmentManager();
        Fragment fr = fm.findFragmentById(R.id.frame);

        switch (v.getId()){
            case R.id.btnAdd:
                if(fr == null){
                    MyCounterFragment cf = new MyCounterFragment();
                    FragmentTransaction tr = fm.beginTransaction();
                    tr.add(R.id.frame,cf,"counter");
                    tr.commit();
                }else{
                    Toast.makeText(this,"이미 추가..", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnRemove:
                if(fr == null){
                    Toast.makeText(this,"fragment 없으", Toast.LENGTH_LONG).show();
                }else{
                    FragmentTransaction tr = fm.beginTransaction();
                    tr.remove(fr);
                    tr.commit();
                }
                break;
            case R.id.btnReplace:
                if(fr == null){
                    Toast.makeText(this,"fragment 없으", Toast.LENGTH_LONG).show();
                }else{
                    FragmentTransaction tr = fm.beginTransaction();
                    if(fr.getTag().equals("counter")){
                        tr.replace(R.id.frame, new MyTextFragment(), "text");
                    }else{
                        tr.replace(R.id.frame, new MyCounterFragment(), "counter");
                    }
                    tr.commit();
                }
                break;
            case R.id.btnHide:
                if(fr == null){
                    Toast.makeText(this,"fragment 없으", Toast.LENGTH_LONG).show();
                }else{
                    FragmentTransaction tr = fm.beginTransaction();
                    if(fr.isHidden()){
                        tr.show(fr);
                    }else{
                        tr.hide(fr);
                    }
                    tr.commit();
                }
                break;

        }
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
