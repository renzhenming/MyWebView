package com.rzm.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.rzm.webview.databinding.ActivityWebViewBinding;
import com.rzm.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity
        implements WebViewFragment.OnFragmentInteractionListener {

    ActivityWebViewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
        mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true) ? View.VISIBLE : View.GONE);
        mBinding.back.setOnClickListener(v -> WebViewActivity.this.finish());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.newInstance(getIntent().getStringExtra(Constants.URL), true);
        transaction.replace(R.id.web_view_fragment, fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }
}
