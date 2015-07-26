package com.example.a.t26_fragment2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public static class TextFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.text_fragment, container, false);
            return root;
        }
    }

    FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMyClick(View v){
        Fragment fr = fm.findFragmentById(R.id.frameLayout);
        FragmentTransaction tr;
        switch (v.getId()){
            case R.id.btnAdd:
                MyFragment mf = new MyFragment();

                tr = fm.beginTransaction();
                tr.add(R.id.frameLayout, mf, "counter");
                tr.addToBackStack(null);
                tr.commit();

                break;
            case R.id.btnRemove:
                if(fr == null){
                    Toast.makeText(this, "fragment 가 없습니다.",Toast.LENGTH_LONG).show();
                }else{
                    tr = fm.beginTransaction();
                    tr.remove(fr);
                    tr.commit();
                }
                break;
            case R.id.btnReplace:
                if(fr == null){
                    Toast.makeText(this, "fragment 가 없습니다.",Toast.LENGTH_LONG).show();
                }else{
                    tr = fm.beginTransaction();
                    if(fr.getTag() == "counter"){
                        TextFragment tf = new TextFragment();
                        tr.replace(R.id.frameLayout, tf, "text");
                    }else{
                        mf = new MyFragment();
                        tr.replace(R.id.frameLayout, mf, "counter");
                    }
                    tr.addToBackStack(null);
                    tr.commit();
                }
                break;
            case R.id.btnHide:
                if(fr == null){
                    Toast.makeText(this, "fragment 가 없습니다.",Toast.LENGTH_LONG).show();
                }else{
                    tr = fm.beginTransaction();
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
