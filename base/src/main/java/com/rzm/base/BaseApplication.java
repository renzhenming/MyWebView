package com.rzm.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
