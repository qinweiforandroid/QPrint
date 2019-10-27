package com.qw.print;

import android.content.Context;

/**
 * Created by qinwei on 2019-10-27 14:22
 * email: qinwei_it@163.com
 */
public class PrintConfig {
    private Context context;
    private int mMaxPrintTaskSize = 3;

    private PrintConfig() {

    }

    public static class Builder {
        public Builder(Context context) {
            this.context = context;
        }

        private Context context;
        private int mMaxPrintTaskSize = 3;

        public Builder setmMaxPrintTaskSize(int mMaxPrintTaskSize) {
            this.mMaxPrintTaskSize = mMaxPrintTaskSize;
            return this;
        }

        public PrintConfig build() {
            PrintConfig config = new PrintConfig();
            config.context = context.getApplicationContext();
            config.mMaxPrintTaskSize = mMaxPrintTaskSize;
            return config;
        }
    }

    public Context getContext() {
        return context;
    }

    public int getMaxPrintTaskSize() {
        return mMaxPrintTaskSize;
    }
}
