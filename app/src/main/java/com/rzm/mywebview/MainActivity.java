package com.rzm.mywebview;

import android.support. v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rzm.base.CommonServiceLoader;
import com.rzm.common.autoservice.IWebViewService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebView(View view) {
        IWebViewService webViewService = CommonServiceLoader.load(IWebViewService.class);
        webViewService.startWebViewActivity(this,"https://www.baidu.com","标题",false);
    }
}
