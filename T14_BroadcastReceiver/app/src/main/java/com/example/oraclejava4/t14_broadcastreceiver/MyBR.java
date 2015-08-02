package com.example.oraclejava4.t14_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by oraclejava4 on 15. 8. 2..
 */
public class MyBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Object[] pdus = (Object[]) bundle.get("pdus");

            String str = "";
            for(int i=0;i<pdus.length;i++){
                SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str += "SMS from" + msg.getOriginatingAddress()
                        + ": "
                        + msg.getMessageBody() +"/n";
            }
            Log.d("MyBR", str);

        }
    }
}
