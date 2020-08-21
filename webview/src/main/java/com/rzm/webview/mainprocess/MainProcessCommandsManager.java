package com.rzm.webview.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.rzm.base.BaseApplication;
import com.rzm.utils.LogUtils;
import com.rzm.webview.IWebViewProcessToMainProcessAidlInterface;

import java.util.Map;

public class MainProcessCommandsManager extends IWebViewProcessToMainProcessAidlInterface.Stub {
    private static MainProcessCommandsManager sInstance;

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                sInstance = new MainProcessCommandsManager();
            }
        }
        return sInstance;
    }

    private MainProcessCommandsManager() {

    }

    public void executeCommand(String commandName, Map params) {
        if ("showToast".equalsIgnoreCase(commandName)) {
            LogUtils.d("MainProcessCommandsManager begin commandName = " + commandName);
        } else if ("openPage".equalsIgnoreCase(commandName)) {
            LogUtils.d("MainProcessCommandsManager begin commandName = " + commandName);
            String targetClass = String.valueOf(params.get("target_class"));
            if (!TextUtils.isEmpty(targetClass)) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(BaseApplication.mApplication, targetClass));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.mApplication.startActivity(intent);
            }
        }
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams) {
        LogUtils.d("MainProcessCommandsManager handleWebCommand commandName = " + commandName);
        executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class));
    }
}
