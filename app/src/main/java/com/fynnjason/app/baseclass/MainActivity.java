package com.fynnjason.app.baseclass;

import android.util.Log;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        Log.e("MainActivity", "initData: " + getActivity().getLocalClassName());
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }
}
