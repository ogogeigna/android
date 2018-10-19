package com.angie.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("service", "bind-onCreate()");

    }


    // TODO: 需要自己创建class to inherit Binder接口, 不能直接使用，包含我们的data
    public class MyBinder extends Binder {

        public MyBindService getService() {
            return MyBindService.this;
        }

    }



    @Override
    public IBinder onBind(Intent intent) {

        Log.d("service", "bind-onBind()");

        // TODO: Return the communication channel to the service.
        return new MyBinder();

    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);

        Log.d("service", "bind-unbindService()");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("service", "bind-onDestroy()");

    }

    public void Play() {
        Log.d("service", "Play");
    }
    public void Pause() {
        Log.d("service", "Pause");
    }
    public void Previous() {
        Log.d("service", "Previous");
    }
    public void Next() {
        Log.d("service", "Next");
    }
}
