package com.example.c.p01_mediaplayer;

import android.content.Intent;
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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;


public class PlayActivity extends ActionBarActivity {

    MediaPlayer mp = null;
    ProgressBar progressBar;
    String strName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        strName =  intent.getExtras().get("name").toString();

        Log.d("P01 playactivity", "listview id : " + strName );
        TextView title = (TextView) findViewById(R.id.textView);
        title.setText(strName);

        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnStop = (Button)findViewById(R.id.btnStop);
        Button btnPause = (Button)findViewById(R.id.btnPause);

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

                    if(mp != null) {
                        try{
                            if(mp.isPlaying()) {
                                //float progress = (float)mp.getCurrentPosition() / (float)mp.getDuration();
                                //progressBar.setProgress((int){progress * 100});

                                Log.d("Mediaplayer", "mp.getCurrentPosition() : " + mp.getCurrentPosition());
                                Log.d("Mediaplayer", "mp.getDuration() : " + mp.getDuration());
                                progressBar.setProgress(mp.getCurrentPosition());
                            }
                        }catch(Exception e){

                        }


                    }
                }
            }
        }).start();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().toString(); // /storage/emulated/0
                path += "/Music/frozen/" + strName;

//                if (mp == null) {
                    mp = new MediaPlayer();
//                }

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

/*        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.pause();
                }
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play, menu);
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
