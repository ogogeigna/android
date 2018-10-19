package com.angie.systemservice;

import android.app.ActivityManager;
import android.content.ContentProvider;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.id.activity_main)
        // 得到布局填充器
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        setContentView(view);
    }

    public void doClick(View view) {

        switch (view.getId()) {
            case R.id.network:
                if (isNetworkConnected(MainActivity.this) == true) {
                    Toast.makeText(MainActivity.this, "network is available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "network is not available", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.wifi:
                WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

                if (mWifiManager.isWifiEnabled()) {
                    mWifiManager.setWifiEnabled(false);
                    Toast.makeText(MainActivity.this, "Wifi is turned off", Toast.LENGTH_SHORT).show();
                } else {
                    mWifiManager.setWifiEnabled(true);
                    Toast.makeText(MainActivity.this, "Wifi is turned on", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.volume:
                AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);

                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                Toast.makeText(MainActivity.this,
                        "The maximum volume of the system is: " + maxVolume + ", current volume is: " + currentVolume,
                        Toast.LENGTH_SHORT).show();
                break;

//            case R.id.process:
//                ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
//
//                String aPackage = activityManager.getRunningAppProcesses().get(0).toString();
//                Toast.makeText(MainActivity.this, "current package name is:" + aPackage, Toast.LENGTH_SHORT).show();
//                break;
        }

    }

    // TODO: determine whether the Network is connected or not
    public boolean isNetworkConnected(Context context) {

        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isConnected();
            }
        }

        return false;
    }

}
