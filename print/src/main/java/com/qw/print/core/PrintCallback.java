package com.qw.print.core;

/**
 * Created by qinwei on 2019/1/3 10:16 AM
 * email: qin.wei@mwee.cn
 */

public interface PrintCallback {
    void onSuccess();

    void onFailure(PrintException e);
}
