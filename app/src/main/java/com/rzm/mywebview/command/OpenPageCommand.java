package com.rzm.mywebview.command;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.rzm.base.BaseApplication;
import com.rzm.utils.LogUtils;
import com.rzm.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.rzm.webview.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class OpenPageCommand implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        LogUtils.d("OpenPageCommand execute = " + parameters.size());
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.mApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.mApplication.startActivity(intent);
        }
    }
}
