package com.example.a.t23_br3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by a on 2015-04-20.
 */
public class MyCustomBR extends BroadcastReceiver {
    NotificationManager manager;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "BroadCast Receiver!!", Toast.LENGTH_LONG).show();
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification noti = new Notification(android.R.drawable.ic_dialog_alert,
                "!!!!BroadCast Receiver Test", System.currentTimeMillis());
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context,0,i,0);
        noti.setLatestEventInfo(context, "BroadCast Receiver Test",
                "@@@BroadCast Receiver Test", pi);
        manager.notify(1234, noti);
    }
}
