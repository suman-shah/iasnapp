package com.project75.ioeallsubjectnotes.adapters;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Context;
import android.widget.Toast;

public class MyWebViewClient extends WebViewClient {
    private Context context;

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        // Show a custom error message or load a custom error page
        view.loadUrl("file:///android_asset/error.html");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    // Optional: Prevent loading of the URL if there is no internet connection
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}