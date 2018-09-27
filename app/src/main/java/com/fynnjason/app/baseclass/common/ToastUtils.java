package com.fynnjason.app.baseclass.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by FynnJason on on 2018/9/20.
 * Function：Toast工具类
 */
public class ToastUtils {
    private static Toast sToast = null;

    private static void showMessage(Context context, final String msg, int len) {
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, msg, len);
        sToast.show();
    }

    public static void show(Context context, String msg) {
        showMessage(context, msg, Toast.LENGTH_SHORT);
    }
}
