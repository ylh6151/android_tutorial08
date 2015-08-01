package com.example.c.t11_mediaplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    MediaPlayer mp = null;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnStop = (Button)findViewById(R.id.btnStop);
        Button btnPause = (Button)findViewById(R.id.btnPause);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if (mp != null) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(mp.isPlaying()) {

                            //float progress = (float)mp.getCurrentPosition() / (float)mp.getDuration();
                            //progressBar.setProgress((int){progress * 100});

                            //start 버튼 클릭시 setMax 하고..
                            Log.d("Mediaplayer", "mp.getCurrentPosition() : " + mp.getCurrentPosition());
                            Log.d("Mediaplayer", "mp.getDuration() : " + mp.getDuration());
                            progressBar.setProgress(mp.getCurrentPosition());
                        }
                    }
                }
            }
        }).start();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().toString();
                path += "/Music/frozen/05 - Let It Go.mp3";

                mp = new MediaPlayer();
                try {
                    mp.setDataSource(path);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                progressBar.setMax(mp.getDuration());
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.stop();
                    mp.release();
                    mp = null;
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.pause();
                    //mp.release();
                }
            }
        });
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
