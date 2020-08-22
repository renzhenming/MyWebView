package com.rzm.mywebview.command;

import com.google.auto.service.AutoService;
import com.rzm.base.CommonServiceLoader;
import com.rzm.common.autoservice.IUserCenterService;
import com.rzm.utils.LogUtils;
import com.rzm.webview.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class LoginCommand implements Command {

    IUserCenterService mUserCenterService = CommonServiceLoader.load(IUserCenterService.class);

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters) {
        LogUtils.d("LoginCommand execute parameters = " + parameters.toString());
        if (mUserCenterService != null && !mUserCenterService.hasLogin()){
            mUserCenterService.login();
        }
    }
}
