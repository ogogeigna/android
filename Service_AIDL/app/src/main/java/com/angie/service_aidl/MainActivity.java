package com.angie.service_aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bind;
    private Button callService;
    private DataService dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: 启动服务， 一旦启动不会停止，除非手动停止
        Intent intent = new Intent (MainActivity.this, MyService.class);
        startService(intent);


        bind = findViewById(R.id.bind);
        callService = findViewById(R.id.call);

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DataService.class.getName());
                bindService(intent, conn, Context.BIND_AUTO_CREATE);

            }
        });

        callService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int result = dataService.getData("hello");
                    Log.d("aidl", "------>" + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    // TODO: 进行两端服务连接： Server - Client
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            dataService = DataService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
