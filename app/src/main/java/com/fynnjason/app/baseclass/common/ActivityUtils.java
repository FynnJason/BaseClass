package com.fynnjason.app.baseclass.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FynnJason
 * Function：activity管理工具
 */
public class ActivityUtils {
    public static List<Activity> sActivityList = new ArrayList<>();

    /**
     * 移除activity
     *
     * @param activity activity
     */
    public static void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    /**
     * 添加activity
     *
     * @param activity activity
     */
    public static void addActivity(Activity activity) {
        boolean have = false;
        for (int i = 0; i < sActivityList.size(); i++) {
            if (sActivityList.get(i) == activity) {
                have = true;
            }
        }
        if (!have) {
            sActivityList.add(activity);
        }
    }

    /**
     * finish所有添加的activity并清空
     */
    public static void finishAll() {
        for (int i = 0; i < sActivityList.size(); i++) {
            if (!sActivityList.get(i).isFinishing()) {
                sActivityList.get(i).finish();
            }
        }
        sActivityList.clear();
    }
}
