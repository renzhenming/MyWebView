package com.xiangxue.usercenter;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.rzm.base.BaseApplication;
import com.rzm.common.autoservice.IUserCenterService;

@AutoService({IUserCenterService.class})
public class IUserCenterServiceImpl implements IUserCenterService {

    @Override
    public boolean hasLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.mApplication, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.mApplication.startActivity(intent);
    }
}
