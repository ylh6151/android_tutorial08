package com.example.oraclejava4.t16_broadcastreceiver3;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by oraclejava4 on 15. 8. 2..
 */
public class MyCustomBR extends BroadcastReceiver {
    NotificationManager manager;
    @Override
    public void onReceive(Context context, Intent intent) {
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
