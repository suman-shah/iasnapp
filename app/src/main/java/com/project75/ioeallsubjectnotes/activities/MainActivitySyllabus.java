package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.project75.ioeallsubjectnotes.R;

public class MainActivitySyllabus extends AppCompatActivity {

    private WebView webviewsyllabus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_syllabus);

        webviewsyllabus = findViewById(R.id.webviewsyllabus);

        WebSettings webSettings = webviewsyllabus.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Handle URL loading within the WebView
        webviewsyllabus.setWebViewClient(new WebViewClient() {
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
        });

        // Load the initial page
        webviewsyllabus.loadUrl("https://drive.google.com/drive/folders/1zvW3vZnw2qxBxo3TgNu3nLY89waFn8sW");
    }

    @Override
    public void onBackPressed() {
        if (webviewsyllabus.canGoBack()) {
            webviewsyllabus.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
