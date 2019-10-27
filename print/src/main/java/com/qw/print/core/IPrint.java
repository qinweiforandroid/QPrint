package com.qw.print.core;

/**
 * Created by qinwei on 2019/1/2 6:25 PM
 * email: qin.wei@mwee.cn
 */
public interface IPrint {

    void checkPrint() throws PrintException;

    void connect() throws PrintException;

    boolean write(byte[] bytes);

    byte[] read(int targetLength) throws PrintException;

    void close() throws PrintException;


}
