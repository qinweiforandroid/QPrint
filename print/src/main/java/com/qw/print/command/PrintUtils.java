package com.qw.print.command;

import com.qw.print.core.PrintConstants;
import com.qw.print.core.PrintItem;
import com.qw.print.ticket.BaseRowTicket;
import com.qw.print.ticket.FourColumnRowTicket;
import com.qw.print.ticket.LabelRowTicket;
import com.qw.print.ticket.RowType;
import com.qw.print.ticket.ThreeColumnRowTicket;
import com.qw.print.ticket.TitleRowTicket;
import com.qw.print.ticket.TwoColumnRowTicket;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by qinwei on 2019/1/4 1:46 PM
 */

public class PrintUtils {


    public static PrintItem getLabel(String content) {
        PrintItem item = PrintItem.create();
        item.setStretchType(PrintConstants.STRETCH_NONE);
        item.setBold(true);
        item.setText(content);
        item.setAlign(PrintConstants.ALIGN_LEFT);
        return item;
    }

    /**
     * 打印纸一行最大的字节
     */
    private static final int LINE_BYTE_SIZE = 48;
    private static final int FL1 = 22;
    private static final int FL2 = 8;
    private static final int FL3 = 9;
    private static final int FL4 = 9;
    private static final int TL1 = 24;
    private static final int TL2 = 12;
    private static final int TL3 = 12;

    private static final int T1 = 24;
    private static final int T2 = 24;


    /**
     * 构建4列格式字符串
     *
     * @param l1 第一列内容
     * @param l2 第二列内容
     * @param l3 第三列内容
     * @param l4 第四列内容
     * @return 格式化后字符
     */
    public static String format(String l1, String l2, String l3, String l4) {
        StringBuilder sb = new StringBuilder();
        sb.append(l1);
        int l1_l2_space = FL1 + FL2 - getBytesLength(l1) - getBytesLength(l2);
        for (int i = 0; i < l1_l2_space; i++) {
            sb.append(" ");
        }
        sb.append(l2);
        int l2_l3_space = FL3 - getBytesLength(l3);
        for (int i = 0; i < l2_l3_space; i++) {
            sb.append(" ");
        }
        sb.append(l3);

        int l3_l4_space = FL4 - getBytesLength(l4);
        for (int i = 0; i < l3_l4_space; i++) {
            sb.append(" ");
        }
        sb.append(l4);
        return sb.toString();
    }

    /**
     * 打印三列
     *
     * @param l1 第一列内容
     * @param l2 第二列内容
     * @param l3 第三列内容
     * @return
     */
    public static String format(String l1, String l2, String l3) {
        StringBuilder sb = new StringBuilder();
        sb.append(l1);
        int l1_l2_space = TL1 + TL2 - getBytesLength(l1) - getBytesLength(l2);
        for (int i = 0; i < l1_l2_space; i++) {
            sb.append(" ");
        }
        sb.append(l2);
        int l2_l3_space = TL3 - getBytesLength(l3);
        for (int i = 0; i < l2_l3_space; i++) {
            sb.append(" ");
        }
        sb.append(l3);
        return sb.toString();
    }

    /**
     * 打印两列
     *
     * @param t1 左侧文字
     * @param t2 右侧文字
     * @return
     */
    public static String format(String t1, String t2) {
        StringBuilder sb = new StringBuilder();
        sb.append(t1);
        int l1_l2_space = T1 + T2 - getBytesLength(t1) - getBytesLength(t2);
        for (int i = 0; i < l1_l2_space; i++) {
            sb.append(" ");
        }
        sb.append(t2);
        return sb.toString();
    }

    public static String getSingleLine() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LINE_BYTE_SIZE; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    public static String getDoubleLine() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LINE_BYTE_SIZE; i++) {
            sb.append("=");
        }
        return sb.toString();
    }

    private static int getBytesLength(String msg) {
        return msg.getBytes(Charset.forName("GB2312")).length;
    }

    /**
     * 小票row样式翻译打印描述
     *
     * @param rowTickets
     * @return
     */
    public static ArrayList<PrintItem> convertPrintItem(ArrayList<BaseRowTicket> rowTickets) {
        ArrayList<PrintItem> printItems = new ArrayList<>();
        PrintItem p;
        for (BaseRowTicket row : rowTickets) {
            p = PrintItem.create();
            p.setAlign(row.getAlign());
            p.setStretchType(row.getStretchType());
            p.setBold(row.isBold());
            switch (row.getType()) {
                case RowType.TITLE:
                    TitleRowTicket title = (TitleRowTicket) row;
                    p.setText(title.getTitle());
                    printItems.add(p);
                    break;
                case RowType.TWO_COLUMN:
                    TwoColumnRowTicket two = (TwoColumnRowTicket) row;
                    p.setText(PrintUtils.format(two.getText1(), two.getText2()));
                    printItems.add(p);
                    break;
                case RowType.THREE_COLUMN:
                    ThreeColumnRowTicket three = (ThreeColumnRowTicket) row;
                    p.setText(PrintUtils.format(three.getText1(), three.getText2(), three.getText3()));
                    printItems.add(p);
                    break;
                case RowType.FOUR_COLUMN:
                    FourColumnRowTicket four = (FourColumnRowTicket) row;
                    p.setText(PrintUtils.format(four.getText1(), four.getText2(), four.getText3(), four.getText4()));
                    printItems.add(p);
                    break;
                case RowType.SINGLE_LINE:
                    p.setText(PrintUtils.getSingleLine());
                    printItems.add(p);
                    break;
                case RowType.DOUBLE_LINE:
                    p.setText(PrintUtils.getDoubleLine());
                    printItems.add(p);
                    break;
                case RowType.LABEL:
                    LabelRowTicket label = (LabelRowTicket) row;
                    p.setText(label.getText());
                    printItems.add(p);
                    break;
                default:
                    break;
            }
        }
        return printItems;
    }
}
