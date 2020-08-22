package com.rzm.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.rzm.base.BaseApplication;
import com.rzm.utils.LogUtils;
import com.rzm.webview.IWebViewProcessToMainProcessAidlInterface;
import com.rzm.webview.mainprocess.MainProcessCommandService;

public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private static volatile WebViewProcessCommandDispatcher mInstance;
    private IWebViewProcessToMainProcessAidlInterface iWebviewProcessToMainProcessInterface;

    public static WebViewProcessCommandDispatcher getInstance() {
        if (mInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                if (mInstance == null) {
                    mInstance = new WebViewProcessCommandDispatcher();
                }
            }
        }
        return mInstance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.mApplication, MainProcessCommandService.class);
        BaseApplication.mApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        LogUtils.d("onServiceConnected");
        iWebviewProcessToMainProcessInterface = IWebViewProcessToMainProcessAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        LogUtils.d("onServiceDisconnected");
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        LogUtils.d("onBindingDied");
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    /**
     * webview通过这个方法调用到service中的方法
     *
     * @param commandName
     * @param params
     * @param baseWebView
     */
    public void executeCommand(String commandName, String params, final MWebView baseWebView) {
        if (iWebviewProcessToMainProcessInterface != null) {
            try {
                iWebviewProcessToMainProcessInterface.handleWebCommand(commandName, params);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
