package com.example.oraclejava4.t13_service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by oraclejava4 on 15. 8. 2..
 */
public class MyService extends Service {

    NotificationManager manager;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void displayNoti(String msg){
        Notification noti = new Notification(R.mipmap.ic_launcher, msg, System.currentTimeMillis());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);

        noti.setLatestEventInfo(getApplicationContext(), "contentTitle", msg, pIntent);
        manager.notify(1234, noti);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand");
        displayNoti("onStartCommand");
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        displayNoti("onCreate");
        Log.d("MyService", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        displayNoti("onDestroy");
        Log.d("MyService", "onDestroy");
    }
}
