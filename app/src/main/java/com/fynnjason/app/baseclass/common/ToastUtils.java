package com.fynnjason.app.baseclass.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by FynnJason on on 2018/9/20.
 * Function：Toast工具类
 */
public class ToastUtils {

    private static Toast sToast = null;

    /**
     * 有效避免多次调用toast造成的多次显示
     *
     * @param context 上下文
     * @param msg     消息
     * @param len     长度，默认短
     */
    private static void show(Context context, final String msg, int len) {
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, msg, len);
        sToast.show();
    }

    public static void show(Context context, String msg) {
        show(context, msg, Toast.LENGTH_SHORT);
    }
}
