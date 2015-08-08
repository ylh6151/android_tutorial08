package com.example.c.p02_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.Random;

/**
 * Created by c on 2015-08-08.
 */
public class MyService extends Service {

    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    MediaPlayer mp = null;
    private IBinder binder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {

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
                                float progress = (float)mp.getCurrentPosition() / (float)mp.getDuration();
                                progressBar.setProgress((int) {progress * 100});

                                //Log.d("Mediaplayer", "mp.getCurrentPosition() : " + mp.getCurrentPosition());
                                //Log.d("Mediaplayer", "mp.getDuration() : " + mp.getDuration());
                                //progressBar.setProgress(mp.getCurrentPosition());
                            }
                        }catch(Exception e){

                        }


                    }
                }
            }
        }).start();

        return binder;
    }

    public void play() {
        String path = Environment.getExternalStorageDirectory().toString(); // /storage/emulated/0
        path += "/Music/frozen/05 - Let It Go.mp3";

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

        //progressBar.setMax(mp.getDuration());
    }

    public void stop() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void pause(){
        if (mp != null) {
            mp.pause();
        }
    }


}
