package com.fynnjason.app.baseclass.basemvp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import butterknife.ButterKnife;

/**
 * Created by FynnJason on on 2018/9/25.
 * Function：Activity基类 MVP模式使用
 */
public abstract class BaseMvpActivity<V extends MvpView, P extends MvpBasePresenter<V>> extends MvpActivity<V, P> {

    private boolean mEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //黄油刀绑定
        ButterKnife.bind(getActivity());
        //默认情况强制竖屏
        if (!mEnabled)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initData();
        initView();
        initListener();
        loadData();
    }

    /**
     * 视图id
     */
    public abstract int getLayoutId();

    /**
     * 上下文对象
     *
     * @return 当前子类
     */
    public Activity getActivity() {
        return this;
    }

    @NonNull
    public abstract P createPresenter();

    /**
     * 初始化数据
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
     * 剩余的逻辑代码，加载数据都放在这里处理
     */
    public abstract void loadData();

    /**
     * 防止Fragment崩溃后重置Activity导致的Fragment重叠问题
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    /**
     * 暴露设置横竖屏切换的接口
     *
     * @param enabled 是否可以横竖屏的切换
     */
    public void setOrientationEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    //--------------------------------------隐藏软键盘方案---------------------------------------------//
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
