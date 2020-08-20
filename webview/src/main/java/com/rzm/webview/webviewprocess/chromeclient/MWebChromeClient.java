package com.rzm.webview.webviewprocess.chromeclient;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.rzm.utils.LogUtils;
import com.rzm.webview.webviewprocess.client.MWebViewCallBack;

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

    /**
     * 用来将H5代码的log信息打印到logcat
     */
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        LogUtils.d(consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
