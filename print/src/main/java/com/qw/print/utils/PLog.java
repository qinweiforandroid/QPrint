package com.qw.print.utils;

import android.util.Log;

/**
 * Created by qinwei on 2019/1/3 11:27 AM
 */
public class PLog {
    public final static String TAG = "PLog";
    private static boolean isDebug = true;

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void setDebug(boolean isDebug) {
        PLog.isDebug = isDebug;
    }
}
