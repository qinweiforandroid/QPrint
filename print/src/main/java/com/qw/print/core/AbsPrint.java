package com.qw.print.core;

/**
 * Created by qinwei on 2019-10-27 14:53
 * email: qinwei_it@163.com
 */
public abstract class AbsPrint implements IPrint {
    protected PrintRequest mRequest;

    public AbsPrint(PrintRequest request) {
        this.mRequest = request;
        if (request == null) {
            throw new IllegalArgumentException("request must not be null");
        }
    }
}
