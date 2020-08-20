package com.rzm.mywebview;

import android.os.Bundle;
import android.view.View;

import com.rzm.base.CommonServiceLoader;
import com.rzm.common.autoservice.IWebViewService;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebView(View view) {
        IWebViewService webViewService = CommonServiceLoader.load(IWebViewService.class);
        webViewService.startLocalHtml(MainActivity.this);
        // webViewService.startWebViewActivity(this,"https://www.baidu.com","标题",true);
    }
}
