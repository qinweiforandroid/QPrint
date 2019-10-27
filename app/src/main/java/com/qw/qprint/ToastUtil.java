package com.qw.qprint;

import android.widget.Toast;

/**
 * Created by qinwei on 2018/12/1.
 */

public class ToastUtil {

    public static void showToast(String msg) {
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String msg, int time) {
        Toast.makeText(MyApplication.getInstance(), msg, time).show();
    }

    public static void showToastLong(String msg) {
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
