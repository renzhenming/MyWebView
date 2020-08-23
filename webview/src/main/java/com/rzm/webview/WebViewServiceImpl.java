package com.rzm.webview;

import android.content.Context;
import com.rzm.common.autoservice.IWebViewService;
import com.rzm.webview.utils.Constants;
import com.google.auto.service.AutoService;

import androidx.fragment.app.Fragment;

@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        if (context != null){
            context.startActivity(WebViewActivity.newIntent(context,url,title,isShowActionBar));
        }
    }

    @Override
    public Fragment getWebViewFragment(String url, boolean canNativeRefresh) {
        return WebViewFragment.newInstance(url, canNativeRefresh);
    }

    @Override
    public void startLocalHtml(Context context) {
        if (context != null){
            context.startActivity(WebViewActivity.newIntent(context,Constants.ANDROID_ASSET_URI + "demo.html","本地Demo测试页",true));
        }
    }
}
