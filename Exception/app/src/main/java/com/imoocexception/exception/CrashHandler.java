package com.imoocexception.exception;


/**
 * 捕获全局异常
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    // TODO: 定义默认处理器
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    Context context;


    // TODO: 实现CrashHandler -> 利用singleton单例模式来创建唯一的模式
    private static CrashHandler mInstance;

    // 用来存储设备信息和日常信息
    private Map<String, String> mInfo = new HashMap<>();

    // 文件日期格式
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    // 需要一个无参构造方法
    private CrashHandler() {

    }

    /**
     * Singleton
     *
     * **/
    public static CrashHandler getmInstance() {

        // 懒汉模式
        if (mInstance == null) {
            // TODO: lock for the current class
            synchronized (CrashHandler.class) {
                if (mInstance == null) {
                    mInstance = new CrashHandler();
                }
            }
        }

        return mInstance;
    }


    /**
     * 初始化
     * @param context
     */
    // initialize
    public void init(Context context) {
        this.context = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this); // 'this' refers to CrashHandler()
    }


    // Any exception thrown by this method will be ignored by the Java Virtual Machine.
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        // 1. 收集错误信息 Collect error info
        // 2. 保存错误信息 store error info
        // 3. 上传到服务器 upload to the server


        // 首先判断是否有人为进行处理
        if (!handleException(throwable)) {

            // 未处理，调用系统默认的处理器处理
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, throwable);

            } else {
                // 已经人为处理
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 让当前程序强制退出
                Process.killProcess(Process.myPid());
                System.exit(1);
            }

        }

    }


    /**
     * 人为处理异常
     * @param throwable
     * @return true: handled; false: unhandled
     */
    private boolean handleException(Throwable throwable) {

        if (throwable == null) {
            return false;
        }

        // toast提示
        new Thread() {
            @Override
            public void run() {

                // 1. 先准备一个Looper
                // 2. 后面要紧跟一个循环
                Looper.prepare();

                // 进行处理（根据product manager所给文案来写toast什么信息）
                Toast.makeText(context, "UncaughtException", Toast.LENGTH_SHORT).show();

                Looper.loop();

            }
        }.start();

        // TODO: 收集错误信息
        collectErrorInfo();

        // TODO: store error info
        saveErrorInfo();

        return false;
    }

    private void saveErrorInfo() {
    }

    private void collectErrorInfo() {

        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), packageManager.GET_ACTIVITIES);

            if (packageInfo != null) {
                String versionName = TextUtils.isEmpty(packageInfo.versionName) ? "未设置名称" : packageInfo.versionName;

                String versionCode = packageInfo.versionCode + "";
                mInfo.put("versionName", versionName);
                mInfo.put("versionCode", versionCode);
            }

            // reflect 类里面的Filed????
            // 获取所有属性
            Field[] fields = Build.class.getFields();

            if (fields != null && fields.length > 0) {
                for (Field field: fields) {
                    field.setAccessible(true);

                    try {
                        mInfo.put(field.getName(), field.get(null).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
