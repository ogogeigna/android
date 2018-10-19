package com.angie.webview;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpThread extends Thread {

    private String mUrl;

    public HttpThread(String mUrl) {
        this.mUrl = mUrl;
    }


    // TODO: 完成下载线程类

    @Override
    public void run() {
        super.run();

        try {

            Log.d("webview","download started");

            // TODO: 传进来我们的URL
            URL httpUrl = new URL(mUrl);
            // TODO: 打开一个http url, 可以设置输入流和输出流 => 写入sd card
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // 得到下载流
            InputStream in = conn.getInputStream();

            File downloadFile;
            File sdFile;
            FileOutputStream out = null;

            // TODO: 需要下载到sd card, 所以首先我们要判断sd card是否存在
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                // 拿到外置存储目录
                downloadFile = Environment.getExternalStorageDirectory();

                // 具体存在哪个目录下 => downloadFile这个目录下
                sdFile = new File(downloadFile, "test.apk");

                // 定义一个输出流，在sdFile目录下
                out = new FileOutputStream(sdFile);
            }

            // TODO: 为了得到stream，需要缓存 （6k)
            byte[] b = new byte[6*1024];
            int len;

            // TODO: 不等于-1，说明有流存在； -1表示流终止了
            while ((len = in.read(b)) != -1) {
                if (out != null) {
                    // TODO:从0开始写，读到哪写到哪， 也就是写到len
                    out.write(b, 0, len);
                }
            }

            if (out != null) {
                out.close();
            }

            if (in != null) {
                in.close();
            }

            Log.d("webview","download successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
