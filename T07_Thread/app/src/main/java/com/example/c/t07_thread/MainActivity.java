package com.example.c.t07_thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    class MyThread extends Thread{
        public void run(){
            for(int i = 0; i <20; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("MyThread", "count : " + i);
            }
        }
    }

    TextView textView;
    Handler myHandler;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MyThread th = new MyThread();
        //th.start();

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        myHandler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        textView.setText("count : " + msg.arg1);
                        progressBar.setProgress(msg.arg1);
                        break;
                }
            }
        };

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <50; i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("MyThread2", "count : " + i);
                    Message msg = myHandler.obtainMessage();
                    msg.what = 1;
                    msg.arg1 = i;
                    myHandler.sendMessage(msg);
                    //textView.setText("count : " + i);
                }
            }
        });

        th2.start();
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
