package com.imoocexception.exception;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.print(string.equals("something"));

        // TODO: run 出现 app crash -> Dialog pops up: Exception has stopped

//        Thread.UncaughtExceptionHandler
    }


}
