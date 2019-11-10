package com.qw.print.command;

import com.qw.print.data.PrintItem;

import java.util.ArrayList;

/**
 * Created by qinwei on 2019-11-09 13:56
 * email: qinwei_it@163.com
 */
public class LabelCommandConvert implements ICommandConvert {
    @Override
    public byte[] convert(ArrayList<PrintItem> items) {
        return new byte[0];
    }
}
