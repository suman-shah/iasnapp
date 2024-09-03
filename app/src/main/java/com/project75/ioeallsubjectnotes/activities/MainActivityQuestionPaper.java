package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JavascriptInterface;
import android.view.MotionEvent;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.project75.ioeallsubjectnotes.R;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebResourceError;
import android.widget.Toast;
public class MainActivityQuestionPaper extends AppCompatActivity {

    private WebView webView;
    private View bannerView;
    private float dX, dY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_question_paper);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);  // Enable JavaScript if needed
        webView.getSettings().setDomStorageEnabled(true);  // Enable DOM storage
        webView.getSettings().setLoadWithOverviewMode(true);  // Scale the content to fit the screen
        //webView.getSettings().setUseWideViewPort(true);  // Enable wide viewport to fit content
        bannerView = findViewById(R.id.banner_view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.endsWith(".pdf")) {
                    url = "https://docs.google.com/viewer?url=" + url;
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // Inject CSS into the loaded page
                String css = "body { background-color: #f7f7f7 !important; color: #333 !important; font-family: Arial, sans-serif !important; ... }";
                String js = "var style = document.createElement('style'); style.type = 'text/css'; style.innerHTML = '" + css + "'; document.head.appendChild(style);";
                webView.evaluateJavascript(js, null);

            }
            // Handle errors like no internet connection
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                // Show a custom error message or load a local error page
                view.loadUrl("file:///android_asset/error.html");
                Toast.makeText(MainActivityQuestionPaper.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        // Check for internet connectivity before loading the URL
        if (isNetworkAvailable()) {
             webView.loadUrl("https://bharatbhandari.com.np/bel.html");
        } else {
            // Show a custom error message or load a local error page
            webView.loadUrl("file:///android_asset/error.html");
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        // Set up touch listener for dragging the banner
        bannerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        // Prevent touch events from passing through the banner view
        bannerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; // This prevents touch events from passing through
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.getUrl().equals("file:///android_asset/error.html")) {
            // Handle back press when on the error page
            super.onBackPressed(); // Close the app or activity
        } else if (webView.canGoBack()) {
            webView.goBack(); // Go back in web history
        } else {
            super.onBackPressed();
        }
    }

    // JavaScript interface class
    public class MyJavaScriptInterface {
        @JavascriptInterface
        public void changePageColor(String color) {
            // This method can be called from JavaScript on the page
        }
    }
    // Method to check for network availability
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

