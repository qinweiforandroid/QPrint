package com.qw.print.core;

import com.qw.print.ticket.BaseRowTicket;

import java.util.ArrayList;

/**
 * Created by qinwei on 2019/1/3 10:13 AM
 * email: qin.wei@mwee.cn
 */

public class PrintRequest {
    /**
     * 打印机接口类型
     */
    public PrintType type;

    /**
     * 小票行集合描述
     */
    private ArrayList<PrintItem> printItems = new ArrayList<>();

    /**
     * 小票行样式集合描述
     */
    private ArrayList<BaseRowTicket> rowTickets = new ArrayList<>();
    /**
     * 打印机指令类型
     */
    private int commandType;

    /**
     * ip地址
     */
    public String ip;
    /**
     *
     */
    public String macAddress;
    public String deviceId;
    public String tag;
    public int state;

    public int getCommandType() {
        return commandType;
    }

    public void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public ArrayList<PrintItem> getPrintItems() {
        return printItems;
    }

    public ArrayList<BaseRowTicket> getRowTickets() {
        return rowTickets;
    }

    public void setRowTickets(ArrayList<BaseRowTicket> rowTickets) {
        this.rowTickets = rowTickets;
    }

    public void addPrintItem(PrintItem item) {
        printItems.add(item);
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