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


public class Activity_B extends AppCompatActivity {
    //store the url of website of interest in constant field
    private static final String ANDELA_URL = "https://andela.com/alc/";

    WebView browser=null;
    //checks if the splash screen is on
    private boolean splashOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);
        WebViewStickler stickler = new WebViewStickler();
        browser = findViewById(R.id.webview);

        //restore state instead of creating new browser on configuration changes
        if (savedInstanceState != null) {

            browser.restoreState(savedInstanceState);

        }


        //otherwise if no savedstate, create browser and configure it for optimal display of webpage
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

//placeholder while webpage loads
        browser.setBackgroundColor(0);
        browser.setBackgroundResource(R.drawable.gourd);
//load cache whenever available to save data
        browser.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        if (!isNetworkAvailable()) {
            //load cahe if no network is available
            browser.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        } else {
            //otherise load webpage corresponding to url with the defined settings
            browser.loadUrl(ANDELA_URL);

        }}

    }




//save the webview instance on configuration changes
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        browser.saveState(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }
//returns a boolean indicating network connectivity
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

 //our webview's webclient
    private class WebViewStickler extends WebViewClient {
        private static final String SSL_ERROR = "This website has an unverified certificate";
        private static final String ANDELA_HOMEPAGE = "andela.com/alc/";
//allow use of navigation of webpage instead of app
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            if (url.indexOf(ANDELA_HOMEPAGE) > -1) return false;
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!splashOn) {
                browser.setBackgroundDrawable(null);
                browser.setBackgroundColor(0);

                splashOn = true;
            }

            super.onPageFinished(view, url);
        }

     //handle errors resulting from unsuccessfull handshake from SSL certificate and display waring to user
        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {

            Toast.makeText(getApplicationContext(), SSL_ERROR, Toast.LENGTH_LONG).show();
            handler.proceed();
        }


    }

    //allow use of back button to navigate webpage instead of app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {
            browser.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}