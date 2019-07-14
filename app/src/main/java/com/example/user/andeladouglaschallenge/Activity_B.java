package com.example.user.andeladouglaschallenge;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;
public class Activity_B extends AppCompatActivity {
    private static final String ANDELA_URL = "https://andela.com/alc/";
    WebView browser=null;
    File file;
    private boolean isSplashOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);
        WebViewStickler stickler = new WebViewStickler();
        browser = findViewById(R.id.webview);

        if (savedInstanceState != null) {

            browser.restoreState(savedInstanceState);

        }
        else {

        browser.setWebViewClient(stickler);
        browser.getSettings().setAllowContentAccess(true);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setDomStorageEnabled(true);
        browser.getSettings().setAllowFileAccess(true);
        browser.getSettings().setAppCacheEnabled(true);


        browser.setBackgroundColor(0);
        browser.setBackgroundResource(R.drawable.gourd);

        browser.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        if (!isNetworkAvailable()) { // loading offline
            browser.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        } else {

            browser.loadUrl(ANDELA_URL);

        }}

    }





    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        browser.saveState(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class WebViewStickler extends WebViewClient {
        private static final String SSL_ERROR = "This website has an unverified certificate";
        private static final String ANDELA_HOMEPAGE = "andela.com/alc/";

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            if (url.indexOf(ANDELA_HOMEPAGE) > -1) return false;
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!isSplashOn) {
                browser.setBackgroundDrawable(null);
                browser.setBackgroundColor(0);

                isSplashOn = true;
            }

            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
            // for SSLErrorHandler

            Toast.makeText(getApplicationContext(), SSL_ERROR, Toast.LENGTH_LONG).show();
            handler.proceed();
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {
            browser.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}