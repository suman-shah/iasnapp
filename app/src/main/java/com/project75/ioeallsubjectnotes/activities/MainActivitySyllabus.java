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
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.project75.ioeallsubjectnotes.R;

public class MainActivitySyllabus extends AppCompatActivity {

    private WebView webviewsyllabus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_syllabus);

        webviewsyllabus = findViewById(R.id.webviewsyllabus);

        // Enable JavaScript for the WebView
        WebSettings webSettings = webviewsyllabus.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set a custom WebViewClient to handle URL loading and errors
        webviewsyllabus.setWebViewClient(new WebViewClient() {
            // For Android 7.0 and above
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();

                if (url.endsWith(".pdf")) {
                    // Use Google Docs Viewer to display PDFs
                    url = "https://docs.google.com/viewer?url=" + url;
                }

                view.loadUrl(url);
                return true;
            }

            // For Android versions below 7.0
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    // Use Google Docs Viewer to display PDFs
                    url = "https://docs.google.com/viewer?url=" + url;
                }

                view.loadUrl(url);
                return true;
            }

            // Handle errors like no internet connection
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                // Show a custom error message or load a local error page
                view.loadUrl("file:///android_asset/error.html");
                Toast.makeText(MainActivitySyllabus.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        // Check for internet connectivity before loading the URL
        if (isNetworkAvailable()) {
            webviewsyllabus.loadUrl("https://drive.google.com/drive/folders/1zvW3vZnw2qxBxo3TgNu3nLY89waFn8sW");
        } else {
            // Show a custom error message or load a local error page
            webviewsyllabus.loadUrl("file:///android_asset/error.html");
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle back button press to navigate within the WebView's history
    @Override
    public void onBackPressed() {
        if (webviewsyllabus.canGoBack()) {
            webviewsyllabus.goBack();
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