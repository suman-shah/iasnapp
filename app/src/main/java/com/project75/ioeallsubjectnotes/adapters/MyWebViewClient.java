package com.project75.ioeallsubjectnotes.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MyWebViewClient extends WebViewClient {
    private Context context;
    private static final String ERROR_PAGE_URL = "file:///android_asset/error.html";

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        // Inject custom CSS if needed
        String css = "body { background-color: #f7f7f7 !important; color: #333 !important; font-family: Arial, sans-serif !important; }";
        String js = "var style = document.createElement('style'); style.type = 'text/css'; style.innerHTML = '" + css + "'; document.head.appendChild(style);";
        view.evaluateJavascript(js, null);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        if (url.endsWith(".pdf")) {
            // Redirect to Google Docs for PDF viewing
            url = "https://docs.google.com/viewer?url=" + url;
        }
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        // Check if the current page is already the error page to prevent infinite looping
        if (!view.getUrl().equals(ERROR_PAGE_URL)) {
            view.loadUrl(ERROR_PAGE_URL);
            Toast.makeText(context, "No internet connection or error loading page", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        // Show a warning instead of proceeding immediately; use `handler.proceed()` cautiously
        Toast.makeText(context, "SSL Certificate error. Please check your connection.", Toast.LENGTH_SHORT).show();
        handler.cancel(); // Block SSL error for security
    }

    // Method to check for network availability
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
