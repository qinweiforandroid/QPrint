package com.qw.qprint;

import android.app.Application;

import com.qw.framework.AppStateTracker;
import com.qw.framework.utils.Trace;
import com.qw.print.PrintConfig;
import com.qw.print.PrintManager;


/**
 * Created by qinwei on 2016/9/24 19:41
 * email:qinwei_it@163.com
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        boolean isDebug = BuildConfig.DEBUG;
        AppStateTracker.init(this);
        Trace.model = isDebug;
        Trace.setTag("pos");

        PrintManager.init(new PrintConfig.Builder(this)
                .setMaxPrintTaskSize(3)
                .build());
    }

    public static MyApplication getInstance() {
        return mInstance;
    }


}
