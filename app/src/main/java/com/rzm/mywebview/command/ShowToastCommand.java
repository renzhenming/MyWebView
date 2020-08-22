package com.rzm.mywebview.command;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.rzm.base.BaseApplication;
import com.rzm.webview.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class ShowToastCommand implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map parameters) {
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast.makeText(BaseApplication.mApplication, "弹出Toast", Toast.LENGTH_SHORT).show();
        });
    }
}
