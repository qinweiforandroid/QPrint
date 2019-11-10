package com.qw.print.command;

import com.qw.print.data.PrintItem;

import java.util.ArrayList;

/**
 * Created by qinwei on 2019/4/17 1:55 PM
 * email: qin.wei@mwee.cn
 */
public interface ICommandConvert {
    byte[] convert(ArrayList<PrintItem> items);
}
