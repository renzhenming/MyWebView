package com.rzm.webview.webviewprocess;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.rzm.utils.LogUtils;
import com.rzm.webview.webviewprocess.chromeclient.MWebChromeClient;
import com.rzm.webview.webviewprocess.client.MWebViewCallBack;
import com.rzm.webview.webviewprocess.client.MWebViewClient;
import com.rzm.webview.webviewprocess.settings.MWebSetting;

import androidx.annotation.RequiresApi;

public class MWebView extends WebView {
    public MWebView(Context context) {
        super(context);
        init();
    }

    public MWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        MWebSetting.getInstance().initSetting(this);
        addJavascriptInterface(this, "renzhenmingwebview");
    }

    public void registerCallBack(MWebViewCallBack callBack) {
        setWebChromeClient(new MWebChromeClient(callBack));
        setWebViewClient(new MWebViewClient(callBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        LogUtils.d("received h5 message" + jsParam);
//        if(!TextUtils.isEmpty(jsParam)) {
//            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
//            if(jsParamObject != null) {
//                if("showToast".equalsIgnoreCase(jsParamObject.name)) {
//                    Toast.makeText(getContext(), String.valueOf(new Gson().fromJson(jsParamObject.param, Map.class).get("message")), Toast.LENGTH_LONG).show();
//                }
//            }
//        }
    }
}
