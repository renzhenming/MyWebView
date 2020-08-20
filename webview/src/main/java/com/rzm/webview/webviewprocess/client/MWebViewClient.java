package com.rzm.webview.webviewprocess.client;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rzm.utils.LogUtils;

public class MWebViewClient extends WebViewClient {

    private final MWebViewCallBack callBack;

    public MWebViewClient(MWebViewCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (callBack != null) {
            callBack.pageStarted(url);
        } else {
            LogUtils.e("MWebViewCallBack is null.");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (callBack != null) {
            callBack.pageFinished(url);
        } else {
            LogUtils.e("MWebViewCallBack is null.");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (callBack != null) {
            callBack.onError();
        } else {
            LogUtils.e("MWebViewCallBack is null.");
        }
    }
}
