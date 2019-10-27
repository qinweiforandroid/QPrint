package com.qw.print;

import android.content.Context;

/**
 * Created by qinwei on 2019-10-27 15:19
 * email: qinwei_it@163.com
 */
public class PrintContext {
    private static Context mContext;

    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
