package com.example.a.t17_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by a on 2015-04-17.
 */
public class MyService extends Service {
    @Override
      public IBinder onBind(Intent intent) {
        return null;
    }

    boolean flag;

    @Override
    public void onCreate() {
        super.onCreate();
        flag = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (flag) {
                    i++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("MyService", "count : " + i);
                }
            }
        }).start();
        return START_STICKY;
    }
}
