package com.rzm.mywebview;

import com.kingja.loadsir.core.LoadSir;
import com.rzm.base.BaseApplication;
import com.rzm.loadsir.CustomCallback;
import com.rzm.loadsir.EmptyCallback;
import com.rzm.loadsir.ErrorCallback;
import com.rzm.loadsir.LoadingCallback;
import com.rzm.loadsir.TimeoutCallback;

/**
 * 又是一个下沉的例子
 */
public class DemoApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
