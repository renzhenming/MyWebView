package com.rzm.webview.webviewprocess.client;

public interface MWebViewCallBack {
    void pageStarted(String url);
    void pageFinished(String url);
    void onError();
    void updateTitle(String title);
}
