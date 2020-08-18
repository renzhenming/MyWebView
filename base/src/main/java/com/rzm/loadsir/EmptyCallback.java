package com.rzm.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.rzm.base.R;


public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

}
