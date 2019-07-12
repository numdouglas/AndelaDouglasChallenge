package com.example.user.andeladouglaschallenge;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Activity_B extends AppCompatActivity {
    WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);
        WebViewStickler stickler=new WebViewStickler();

        browser = (WebView) findViewById(R.id.webview);
        browser.setWebViewClient(stickler);
        browser.getSettings().setAllowContentAccess(true);
        browser.getSettings().setLoadsImagesAutomatically(true);
         browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setDomStorageEnabled(true);
         browser.loadUrl("https://andela.com/alc/");


    }

    private class WebViewStickler extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            if(url.indexOf("andela.com/alc/") > -1 ) return false;
            return true;}

        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
        // for SSLErrorHandler

            Toast.makeText(getApplicationContext(),"This website has an unverified certificate",Toast.LENGTH_SHORT);
            handler.proceed();
        }}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {
            browser.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class WebFetcher extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
        @Override
        protected String doInBackground(String... strings) {
          browser.loadUrl(strings[0]);
          return null;
        }
        @Override
        protected void onPostExecute(String result) {
        }
    }
}
