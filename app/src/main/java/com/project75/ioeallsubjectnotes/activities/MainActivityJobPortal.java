package com.project75.ioeallsubjectnotes.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;

public class MainActivityJobPortal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_job_portal);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);  // Enable JavaScript if needed
        webView.getSettings().setDomStorageEnabled(true);  // Enable DOM storage
        webView.getSettings().setLoadWithOverviewMode(true);  // Scale the content to fit the screen
        webView.getSettings().setUseWideViewPort(true);  // Enable wide viewport to fit content
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.loadUrl("file:///android_asset/error.html");
                Toast.makeText(MainActivityJobPortal.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });


        // Check for internet connectivity before loading the URL
        if (isNetworkAvailable()) {
            webView.loadUrl("https://nepelectrics.com/");
        } else {
            // Show a custom error message or load a local error page
            webView.loadUrl("file:///android_asset/error.html");
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to check for network availability
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
