package com.project75.ioeallsubjectnotes.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;

public class MainActivityNotifications extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_notifications);

        webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true); // Enable DOM storage for WebView

        // Handle URL loading within the WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();

                if (url.endsWith(".pdf")) {
                    // Use Google Docs Viewer to display the PDF
                    url = "https://docs.google.com/viewer?url=" + url;
                }

                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    // Use Google Docs Viewer to display the PDF
                    url = "https://docs.google.com/viewer?url=" + url;
                }

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.loadUrl("file:///android_asset/error.html");
                Toast.makeText(MainActivityNotifications.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        // Check for internet connectivity before loading the URL
        if (isNetworkAvailable()) {
            webView.loadUrl("http://exam.ioe.edu.np/");
        } else {
            webView.loadUrl("file:///android_asset/error.html");
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Method to check for network availability
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
