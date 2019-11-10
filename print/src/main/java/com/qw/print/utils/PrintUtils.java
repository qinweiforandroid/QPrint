package com.qw.print.utils;

import com.qw.print.data.style.Align;
import com.qw.print.data.PrintItem;
import com.qw.print.data.style.Stretch;
import com.qw.print.data.Style;
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
        PrintItem item = PrintItem.createText(content);
        item.setStyle(new Style.Builder()
                .setAlign(Align.LEFT)
                .setBold(true)
                .setStretch(Stretch.NONE)
                .build());
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

//    private static final int LINE_BYTE_SIZE = 32;
//    private static final int FL1 = 22;
//    private static final int FL2 = 8;
//    private static final int FL3 = 9;
//    private static final int FL4 = 9;
//    private static final int TL1 = 20;
//    private static final int TL2 = 6;
//    private static final int TL3 = 6;
//
//    private static final int T1 = 16;
//    private static final int T2 = 16;


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
        //根据字节长度进行分组
        String[] result = SubByteString.getSubedStrings(l1, TL1);
        int l1_l2_space;
        int l2_l3_space;
        for (int i = 0; i < result.length; i++) {
            l1 = result[i];
            if (i == 0) {
                sb.append(l1);
                //l2，l3不支持换行
                l1_l2_space = TL1 + TL2 - getBytesLength(l1) - getBytesLength(l2);
                for (int j = 0; j < l1_l2_space; j++) {
                    sb.append(" ");
                }
                sb.append(l2);
                l2_l3_space = TL3 - getBytesLength(l3);
                for (int j = 0; j < l2_l3_space; j++) {
                    sb.append(" ");
                }
                sb.append(l3);
                continue;
            }
            sb.append("\n");
            sb.append(l1);
        }
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
        PrintItem item;
        for (BaseRowTicket row : rowTickets) {
            Style style = new Style.Builder()
                    .setAlign(row.getAlign())
                    .setBold(row.isBold())
                    .setStretch(row.getStretchType())
                    .build();
            switch (row.getType()) {
                case RowType.TITLE:
                    TitleRowTicket title = (TitleRowTicket) row;
                    item = PrintItem.createText(title.getTitle());
                    item.setStyle(style);
                    printItems.add(item);
                    break;
                case RowType.TWO_COLUMN:
                    TwoColumnRowTicket two = (TwoColumnRowTicket) row;
                    item = PrintItem.createText(PrintUtils.format(two.getText1(), two.getText2()));
                    item.setStyle(style);
                    printItems.add(item);
                    break;
                case RowType.THREE_COLUMN:
                    ThreeColumnRowTicket three = (ThreeColumnRowTicket) row;
                    item = PrintItem.createText(PrintUtils.format(three.getText1(), three.getText2(), three.getText3()));
                    item.setStyle(style);
                    printItems.add(item);
                    break;
                case RowType.FOUR_COLUMN:
                    FourColumnRowTicket four = (FourColumnRowTicket) row;
                    item = PrintItem.createText(PrintUtils.format(four.getText1(), four.getText2(), four.getText3(), four.getText4()));
                    item.setStyle(style);
                    printItems.add(item);
                    break;
                case RowType.SINGLE_LINE:
                    item = PrintItem.createText(PrintUtils.getSingleLine());
                    item.setStyle(style);
                    printItems.add(item);
                    break;
                case RowType.DOUBLE_LINE:
                    item = PrintItem.createText(PrintUtils.getDoubleLine());
                    item.setStyle(style);
                    printItems.add(item);
                    break;
                case RowType.LABEL:
                    LabelRowTicket label = (LabelRowTicket) row;
                    item = PrintItem.createText(label.getText());
                    item.setStyle(style);
                    printItems.add(item);
                    break;
                default:
                    break;
            }
        }
        return printItems;
    }
}