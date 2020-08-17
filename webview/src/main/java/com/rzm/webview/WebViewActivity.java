package com.rzm.webview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rzm.webview.databinding.ActivityWebViewBinding;
import com.rzm.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity {

    ActivityWebViewBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_web_view);
        viewBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
        viewBinding.webview.getSettings().setJavaScriptEnabled(true);
        viewBinding.webview.loadUrl(getIntent().getStringExtra(Constants.URL));
        viewBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true)? View.VISIBLE:View.GONE);
        viewBinding.back.setOnClickListener(v -> WebViewActivity.this.finish());
    }
}
