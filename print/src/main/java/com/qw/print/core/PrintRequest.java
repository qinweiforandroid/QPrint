package com.qw.print.core;

import com.qw.print.data.PrintItem;
import com.qw.print.ticket.BaseRowTicket;

import java.util.ArrayList;

/**
 * Created by qinwei on 2019/1/3 10:13 AM
 * email: qin.wei@mwee.cn
 */

public class PrintRequest {
    /**
     * gbk字符占用的宽度，默认是一个全角是2个字符，有些打印机是1.5个字符
     */
    private float charSize = 2;
    /**
     * 主流的尺寸
     * 80mm 48
     * 76mm CPL是42/35
     * 76mm CPL40/33
     * 58mm 32
     */
    private int pageSize = 48;

    /**
     * 小票行集合描述
     */
    private ArrayList<PrintItem> items = new ArrayList<>();

    /**
     * 小票行样式集合描述
     */
    private ArrayList<BaseRowTicket> rowTickets = new ArrayList<>();

    /**
     * 打印机接口类型
     */
    public PrinterType type;

    /**
     * 打印机指令类型
     */
    private int commandType;

    /**
     * ip地址
     */
    public String ip;
    /**
     * 蓝牙mac地址
     */
    public String macAddress;
    /**
     * usb 设备id
     */
    public String deviceId;
    /**
     * 打印tag标记
     */
    public String tag;
    /**
     * 执行状态
     */
    public int state = STATE.IDEL;

    public int getCommandType() {
        return commandType;
    }

    public void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public ArrayList<PrintItem> getItems() {
        return items;
    }

    public ArrayList<BaseRowTicket> getRowTickets() {
        return rowTickets;
    }

    public void setRowTickets(ArrayList<BaseRowTicket> rowTickets) {
        this.rowTickets = rowTickets;
    }

    public void addItem(PrintItem item) {
        items.add(item);
    }

    public void addRowTicket(BaseRowTicket ticket) {
        rowTickets.add(ticket);
    }

    public static final class STATE {
        /**
         * 空闲
         */
        public static final int IDEL = 0;
        /**
         * 进行中
         */
        public static final int ING = 1;
        /**
         * 完成
         */
        public static final int DONE = 2;
        /**
         * 错误
         */
        public static final int ERROR = 3;
    }
}