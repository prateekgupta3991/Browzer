package com.application.news.newsstand;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by prtkgpt on 15/4/16.
 */
public class ViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
