package com.fynnjason.app.baseclass.basemvc;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fynnjason.app.baseclass.common.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by FynnJason on on 2018/9/25.
 * Function：Fragment基类 MVC使用
 */
public abstract class BaseMvcFragment extends Fragment {

    public View rootView;
    public Unbinder unbinder;
    private boolean repeatView = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //避免视图重复加载
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            unbinder = ButterKnife.bind(getFragment(), rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //禁止重新绘制界面
        if (repeatView) {
            repeatView = false;
            initData();
            initView();
            initListener();
            loadData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 视图id
     *
     * @return 视图id
     */
    public abstract int getLayoutId();

    /**
     * 当前Fragment实例
     *
     * @return Fragment
     */
    public Fragment getFragment() {
        return this;
    }

    /**
     * 初始化数据，接收传递数据
     */
    public abstract void initData();

    /**
     * 初始化视图
     */
    public abstract void initView();

    /**
     * 初始化监听事件
     */
    public abstract void initListener();

    /**
     * 剩余逻辑代码
     */
    public abstract void loadData();

    /**
     * 吐司
     *
     * @param msg 消息
     */
    public void toast(String msg) {
        ToastUtils.show(getActivity(), msg);
    }
}
