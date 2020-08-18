package com.rzm.loadsir;


import com.kingja.loadsir.callback.Callback;
import com.rzm.base.R;

public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_error;
    }
}
