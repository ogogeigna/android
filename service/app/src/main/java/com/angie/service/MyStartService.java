package com.angie.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


// TODO: 别忘了要注册<service>

public class MyStartService extends Service {
    @Nullable

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("service", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("service", "onStartCommand()");

        return super.onStartCommand(intent, flags, startId);
    }


    // TODO: onBind() 必须要重写！
    // The system invokes this method by calling bindService() when another component wants to bind with the service (such as to perform RPC).
    @Override
    public IBinder onBind(Intent intent) {

        Log.d("service", "onBind()");

        return null;
    }

    @Override
    public void onDestroy() {

        Log.d("service", "onDestroy()");

        super.onDestroy();
    }
}
