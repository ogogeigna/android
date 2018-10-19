package com.angie.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent1;
    Intent intent2;
    MyBindService service;

    ServiceConnection connection = new ServiceConnection() {

            // 当启动源跟Service连接成功之后将会自动调用这个方法
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                // 将service实例返回并接收
                // 将iBinder进行强制类型转换， 之后得到service实例

                // 基本思想： iBinder -> Local Binder -> .getService() -> 再用service去掉用方法
                // TODO: 从而实现和Service的通信
                service = ((MyBindService.MyBinder) iBinder).getService();
            }

            // 当启动源跟Service的连接意外丢失的时候，会调用这个方法
            // 比如当Service崩溃了或者被强行杀死了
            @Override
            public void onServiceDisconnected(ComponentName componentName) {


            }
    };

    Button bindButton;
    Button unbindButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void doClick(View v) {

        switch (v.getId()) {

            case R.id.start_service:

                intent1 = new Intent(MainActivity.this, MyStartService.class);
                startService(intent1);
                break;

            case R.id.stop_service:
                stopService(intent1);
                break;


            case R.id.bind_service:

                intent2 = new Intent(MainActivity.this, MyBindService.class);

                // 3 params: service, connection, flags
                // flag 要设置，不然不能让service自己创建
                bindService(intent2, connection, Service.BIND_AUTO_CREATE);

                break;


            case R.id.unbind_service:

                unbindService(connection);

                break;

            case R.id.play:
                service.Play();
                break;
            case R.id.pause:
                service.Pause();
                break;
            case R.id.previous:
                service.Previous();
                break;
            case R.id.next:
                service.Next();
                break;

        }

    }
}
