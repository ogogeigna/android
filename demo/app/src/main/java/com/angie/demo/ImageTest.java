package com.angie.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageTest extends Activity {

    static String URL = "http://icons.iconarchive.com/icons/paomedia/small-n-flat/128/cat-icon.png";
    private ProgressBar mProgressBar;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        mProgressBar = findViewById(R.id.progressBar);

        // TODO: 设置传递进去的参数 => execute
        NewsAsyncTask asyncTask = new NewsAsyncTask();
        asyncTask.execute(URL);
    }

    class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressBar.setVisibility(View.VISIBLE);

            Log.d("asy", "onPreExecute()");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.d("asy", "doInBackground()");

            // TODO: 1. 只传进来一个参数，所以就是0位
            String url = params[0];
            Bitmap bitmap = null;   // 初始化bitmap
            URLConnection connection;
            InputStream is;

            // TODO: 2. 获取网络对象 => 耗时操作
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();

                // package
                BufferedInputStream bis = new BufferedInputStream(is);

                // TODO: parse bitmap, 从而转成bitmap
                bitmap = BitmapFactory.decodeStream(bis);

                // 记得要关闭
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            publishProgress();
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            Log.d("asy", "onProgressUpdate()");
        }

        // TODO: RETURN a bitmap => image
        // 获取返回结果
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            mProgressBar.setVisibility(View.GONE);

            mImageView.setImageBitmap(bitmap);

            Log.d("asy", "onPostExecute()");
        }
    }


}
