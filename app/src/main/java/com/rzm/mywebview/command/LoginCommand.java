package com.rzm.mywebview.command;

import android.os.RemoteException;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.rzm.base.CommonServiceLoader;
import com.rzm.common.autoservice.IUserCenterService;
import com.rzm.common.eventbus.LoginEvent;
import com.rzm.utils.LogUtils;
import com.rzm.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.rzm.webview.command.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

@AutoService({Command.class})
public class LoginCommand implements Command {

    IUserCenterService mUserCenterService = CommonServiceLoader.load(IUserCenterService.class);
    private ICallbackFromMainprocessToWebViewProcessInterface callback;
    private String callbacknameFromNativeJs;

    public LoginCommand(){
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        LogUtils.d("LoginCommand execute parameters = " + parameters.toString());
        if (mUserCenterService != null && !mUserCenterService.hasLogin()){
            this.callback = callback;
            this.callbacknameFromNativeJs = parameters.get("callbackname").toString();
            mUserCenterService.login();
        }
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        LogUtils.d("LoginCommand "+event.userName);
        HashMap map = new HashMap();
        map.put("accountName", event.userName);
        if (callback != null){
            try {
                callback.onResult(callbacknameFromNativeJs,new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
