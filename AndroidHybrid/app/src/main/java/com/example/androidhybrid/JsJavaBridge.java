package com.example.androidhybrid;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class JsJavaBridge {
    //同步API
    @JavascriptInterface
    public String testSyn(Object msg) {
        return msg + "［syn call］";
    }

    private Activity activity;
    private WebView webView;

    public JsJavaBridge(Activity activity, WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    @JavascriptInterface
    public void onFinishActivity() {
        activity.finish();
    }

    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(this.activity, msg, Toast.LENGTH_SHORT).show();
    }
}
