package com.example.c.p02_service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    MyService myService;
    boolean isBound;
    ProgressBar progressBar;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(isBound){
                        progressBar.setProgress(myService.getCurrentPosition());
                    }

/*                    if(mp != null) {
                        try{
                            if(mp.isPlaying()) {
                                float progress =(float)mp.getCurrentPosition() / (float)mp.getDuration()  * 100;
                                progressBar.setProgress((int)progress);
                           }
                        }catch(Exception e){
                        }
                    }*/
                }
            }
        }).start();

        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnStop = (Button)findViewById(R.id.btnStop);
        Button btnPause = (Button)findViewById(R.id.btnPause);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               myService.play();
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.stop();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.pause();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(MainActivity.this,MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isBound){
            unbindService(connection);
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
