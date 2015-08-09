package com.example.c.p02_service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    BroadcastReceiver myHeadsetBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
           if(intent.hasExtra("state")){
                if(intent.getIntExtra("state", 0)==0){
                    if(mp != null){
                        if(mp.isPlaying()){
                            mp.pause();
                            Toast.makeText(context, "plug disconnected!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }else if (intent.getIntExtra("state",0)==1){
                }
            }

            /*if( (action.compareTo(Intent.ACTION_HEADSET_PLUG))  == 0)   //if the action match a headset one
            {
                int headSetState = intent.getIntExtra("state", 0);      //get the headset state property
                //int hasMicrophone = intent.getIntExtra("microphone", 0);//get the headset microphone property
                if( (headSetState == 0))// && (hasMicrophone == 0))        //headset was unplugged & has no microphone
                {
                    if (mp != null) {
                        if(mp.isPlaying()){
                            mp.pause();
                            Toast.makeText(context,"unplug headset", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }*/
        }
    };

    MediaPlayer mp = null;
    private IBinder binder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myHeadsetBroadCastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myHeadsetBroadCastReceiver);
    }

    public void play() {
        String path = Environment.getExternalStorageDirectory().toString(); // /storage/emulated/0
        path += "/Music/frozen/05 - Let It Go.mp3";

        if (mp == null) {
            mp = new MediaPlayer();
        }

        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if(mp.isPlaying()) {
                mp.pause();
            }else{
                mp.start();
            }
        }
    }

    public int getCurrentPosition(){
        int progress = 0;
        if(mp != null){
            progress = (int)((float)mp.getCurrentPosition() / (float)mp.getDuration()  * 100);
        }
        return progress;
    }

}
