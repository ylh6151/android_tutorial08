package com.example.a.t18_service2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by a on 2015-04-17.
 */
public class MyBoundService extends Service {
    public class MyBinder extends Binder{
        MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    private IBinder binder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }




    private Random random = new Random();
    public int getRandomNubmer(){
        return random.nextInt(100);
    }

}
