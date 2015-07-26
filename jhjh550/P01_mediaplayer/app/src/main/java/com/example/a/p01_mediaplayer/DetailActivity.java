package com.example.a.p01_mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.io.IOException;


public class DetailActivity extends ActionBarActivity {

    MediaPlayer mp = null;
    String path;
    ProgressBar progressBar;
    SeekBar seekBar;
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 1){
                progressBar.setProgress(msg.arg1);
                seekBar.setProgress(msg.arg1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mp.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo( seekBar.getProgress() );
                mp.start();
            }
        });

        path = Environment.getExternalStorageDirectory().toString();
        path += "/Music";

        Intent intent = getIntent();
        path += "/" + intent.getExtras().get("name").toString();

        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = new MediaPlayer();
                try {
                    mp.setDataSource(path);
                    mp.prepare();
                    mp.start();
                    progressBar.setMax(mp.getDuration());
                    seekBar.setMax(mp.getDuration());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.stop();
                    mp.release();
                    mp = null;
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while( true){
                    if(mp != null){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Message msg = handler.obtainMessage();
                        msg.what = 1;
                        msg.arg1 = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        //progressBar.setProgress(mp.getCurrentPosition());
                        //seekBar.setProgress(mp.getCurrentPosition());
                    }
                }
            }
        }).start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
