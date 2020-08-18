package com.rzm.webview;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.rzm.loadsir.ErrorCallback;
import com.rzm.loadsir.LoadingCallback;
import com.rzm.webview.chromeclient.MWebChromeClient;
import com.rzm.webview.client.MWebViewCallBack;
import com.rzm.webview.client.MWebViewClient;
import com.rzm.webview.databinding.FragmentWebViewBinding;
import com.rzm.webview.utils.Constants;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


public class WebViewFragment extends Fragment implements MWebViewCallBack, OnRefreshListener {

    private OnFragmentInteractionListener mListener;
    private String mUrl;
    private boolean mCanNativeRefresh;
    private FragmentWebViewBinding mBinding;
    private LoadService mLoadService;
    //网页加载发生error的时候会再次回调pageFinish,要做区分
    private boolean mIsError = false;

    public WebViewFragment() {
    }

    public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(Constants.URL, url);
        args.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(Constants.URL);
            mCanNativeRefresh = getArguments().getBoolean(Constants.CAN_NATIVE_REFRESH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);
        this.mBinding.webview.getSettings().setJavaScriptEnabled(true);
        this.mBinding.webview.loadUrl(mUrl);
        this.mBinding.webview.setWebViewClient(new MWebViewClient(this));
        this.mBinding.webview.setWebChromeClient(new MWebChromeClient(this));
        mLoadService = LoadSir.getDefault().register(this.mBinding.refreshLayout, (Callback.OnReloadListener) v -> {
            mLoadService.showCallback(LoadingCallback.class);
            this.mBinding.webview.reload();
        });
        this.mBinding.refreshLayout.setOnRefreshListener(this);
        this.mBinding.refreshLayout.setEnableRefresh(mCanNativeRefresh);
        this.mBinding.refreshLayout.setEnableLoadMore(false);
        return mLoadService.getLoadLayout();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void pageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if(mIsError) {
            mLoadService.showCallback(ErrorCallback.class);
            mBinding.refreshLayout.setEnableRefresh(true);
        } else {
            mLoadService.showSuccess();
            mBinding.refreshLayout.setEnableRefresh(mCanNativeRefresh);
        }
        mBinding.refreshLayout.finishRefresh();

        mIsError = false;
    }

    @Override
    public void onError() {
        mIsError = true;
        mBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void updateTitle(String title) {
        if (mListener != null) {
            mListener.updateTitle(title);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void updateTitle(String title);
    }
}
