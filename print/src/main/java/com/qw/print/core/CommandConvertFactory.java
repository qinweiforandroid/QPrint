package com.qw.print.core;

/**
 * Created by qinwei on 2019/4/17 2:17 PM
 * email: qin.wei@mwee.cn
 */
public class CommandConvertFactory {
    public static ICommandConvert create(int commandType) {
        ICommandConvert convert = null;
        switch (commandType) {
            case PrintConstants.COMMAND_ESC:
                convert = new EscCommandConvert();
                break;
            default:
                break;
        }
        return convert;
    }
}
