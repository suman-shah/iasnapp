package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewPowerElectronics extends AppCompatActivity {

    private WebView webView;
    private String pdfUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_power_electronics);

        webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Retrieve the URL from strings.xml
        pdfUrl = getString(R.string.PowerElectronicsFolder);  // Replace "Folder name from strings.xml file"

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
        });

        // Load the initial page using the URL from the strings.xml
        webView.loadUrl(pdfUrl);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}