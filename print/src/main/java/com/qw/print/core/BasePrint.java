package com.qw.print.core;

/**
 * Created by qinwei on 2019-10-27 14:53
 * email: qinwei_it@163.com
 * @author qinwei
 */
public abstract class BasePrint implements IPrint {
    protected PrintRequest mRequest;

    public BasePrint(PrintRequest request) {
        this.mRequest = request;
        if (request == null) {
            throw new IllegalArgumentException("request must not be null");
        }
    }
}
