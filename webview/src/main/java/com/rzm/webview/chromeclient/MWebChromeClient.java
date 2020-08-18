package com.rzm.webview.chromeclient;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.rzm.utils.LogUtils;
import com.rzm.webview.client.MWebViewCallBack;

public class MWebChromeClient extends WebChromeClient {

    private final MWebViewCallBack callBack;

    public MWebChromeClient(MWebViewCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if(callBack != null) {
            callBack.updateTitle(title);
        } else {
            LogUtils.d("callBack is null");
        }
    }
}
