package com.example.androidhybrid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        //webview设置
        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);

        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:") || url.startsWith("ftp")) {
                    view.loadUrl(url);
                    return true;
                } else if (url.startsWith("scheme://")) {
                    // 使用浏览器打开
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 在 onPageFinished 回调里调用，表示页面加载好就调用
                webview.post(new Runnable() {
                    @Override
                    public void run() {
                        webview.evaluateJavascript("javascript:callJsFunction('hello js')", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                Log.e("WebViewActivity", "js返回的结果： " + s); //日志打印
                            }
                        });
                    }
                });
            }
        });
        webview.addJavascriptInterface(new JsJavaBridge(this, webview), "$Android");
        //dist打包方式引入
        //webview.loadUrl("file:///android_asset/index.html");
        //局域网方式引入
        webview.loadUrl("http://192.168.1.8:8080");
    }

}
