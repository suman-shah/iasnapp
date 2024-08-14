package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;

public class MainActivityJobPortal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_job_portal);

        // Initialize the WebView and load the URL
        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // Ensures the URL opens within the WebView
        webView.loadUrl("https://nepelectrics.com/");
    }
}
