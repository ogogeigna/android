package com.angie.service_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    Binder binder = new DataService.Stub() {

        public int getData(String name) {

            if (name.equals("hello")) {
                return 1;
            }
            return -1 ;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        // return the Interface
        return binder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
