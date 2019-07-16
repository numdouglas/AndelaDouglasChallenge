package com.example.user.andeladouglaschallenge;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import es.dmoral.toasty.Toasty;
import android.view.View.OnClickListener;

//suppress warnings for possible XSS vulnerabilities on enabling javascript in webview
@SuppressLint("SetJavaScriptEnabled")
public class Activity_B extends AppCompatActivity implements OnClickListener {
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
            //load cache if no network is available
            browser.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        } else {
            //otherwise load webpage corresponding to url with the defined settings
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

    @Override
    public void onClick(View view) {

    }

    //our webview's webclient
    private class WebViewStickler extends WebViewClient {
        private static final String ANDELA_HOMEPAGE = "andela.com/alc/";
//allow use of navigation of webpage instead of app
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return url.indexOf(ANDELA_HOMEPAGE) <= -1;
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






     //handle errors resulting from unsuccessful handshake from SSL certificate and display waring to user
        @Override
        public void onReceivedSslError(WebView browser, final SslErrorHandler sslErrorHandler, SslError sslError) {
           //after checking the certificate in raw folder, create an appropriate Toast message in case it does
           //not fulfill any of the requirements of the ssl layer
            String message = "SSL error";
            switch (sslError.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    message ="Untrusted issuing authority for this website's certificate.";
                    break;
                case SslError.SSL_EXPIRED:
                    message = "This website's certificate has expired.";
                    break;
                case SslError.SSL_IDMISMATCH:
                    message = "There is a mismatch with the certificate details and hostname";
                    break;
                case SslError.SSL_NOTYETVALID:
                    message = "This website uses an unvalidated certificate";
                    break;
            }
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.andika);
            Toasty.Config.getInstance().setTextSize(24).setToastTypeface(typeface).tintIcon(true).apply();
            Toasty.warning(getApplicationContext(),message,5000,true).show();
            sslErrorHandler.proceed();
        }


    }

    //allow use of back button to navigate webpage instead of app
    @Override
    public void onBackPressed() {
        if (browser.isFocused() && browser.canGoBack()) {
            browser.goBack();
        } else {
            super.onBackPressed();
        }
    }
}