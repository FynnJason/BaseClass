package com.fynnjason.app.baseclass;

import android.util.Log;

import com.fynnjason.app.baseclass.basemvc.BaseMvcActivity;

public class MainActivity extends BaseMvcActivity {

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

    @Override
    public void loadData() {

    }
}
