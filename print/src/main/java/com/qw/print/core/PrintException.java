package com.qw.print.core;

/**
 * Created by qinwei on 2019/1/3 10:54 AM
 * email: qin.wei@mwee.cn
 */

public class PrintException extends Exception {
    private int code;

    public PrintException(int code, String message) {
        super(message);
        this.code = code;
    }

    public PrintException(String message) {
        super(message);
    }
}
