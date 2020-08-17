package com.rzm.common.autoservice;

import android.content.Context;

/**
 * 接口下沉，每个模块都可以使用，真正的接口隔离
 */
public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);
}
