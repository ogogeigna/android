package com.angie.webview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
//    private Button goBack;
//    private Button refresh;
//    private TextView viewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            webView = findViewById(R.id.webview);
//        goBack = findViewById(R.id.goback);
//        refresh = findViewById(R.id.refresh);
//        viewTitle = findViewById(R.id.title);

        // TODO: 默认是通过浏览器去打开URL， 为了在APP中打开web，要去重写它的方法 ⤵
        webView.loadUrl("https://play.google.com/store/apps");       // 要去添加一个访问网络的权限！！

        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                // TODO: 调用WebView自己的方法去加载URL
//                view.loadUrl(url);
//
//                return super.shouldOverrideUrlLoading(view, url);
//            }
        });


//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//
//                // TODO: 设置title 更美观
//                viewTitle.setText(title);
//
//                super.onReceivedTitle(view, title);
//            }
//        });


//        goBack.setOnClickListener(new listener());
//        refresh.setOnClickListener(new listener());


        // TODO: implement the interface to get the URL information of the current web browser
        // and through the URL, we can download apk file or mp3...
//        webView.setDownloadListener(new MyDownload());


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }


    // 手机后退
    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }

    // TODO: 为Buttons 设置监听事件
//    class listener implements View.OnClickListener{
//
//        @Override
//        public void onClick(View view) {
//
//            switch (view.getId()) {
//
//                case R.id.refresh:
//                    webView.reload();
//                    break;
//
//                case R.id.goback:
//                    finish();
//                    break;
//
//                default:
//                    break;
//            }
//        }
//    }


    // 涉及到下载，肯定就需要tread
    class MyDownload implements DownloadListener{
        @Override
        public void onDownloadStart(String url, String s1, String s2, String s3, long l) {

            Log.d("webview","url ----------->" + url);

            // TODO: Because we wanna download apk, we should check if ends with .apk
            if (url.endsWith(".apk")) {
                // 通过webview内置浏览器下载
                // new HttpThread(url).start();


                // 通过系统浏览器下载
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            }

        }
    }
}
