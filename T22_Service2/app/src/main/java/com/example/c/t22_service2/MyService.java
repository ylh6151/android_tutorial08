package com.example.c.t22_service2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

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

    private IBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Random random = new Random();
    public int getRandomNumber(){
        return random.nextInt(100);
    }
}
