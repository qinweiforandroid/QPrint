package com.qw.print.core;

/**
 * Created by qinwei on 2019/1/2 6:30 PM
 * email: qin.wei@mwee.cn
 */

public class PrintConstants {
    /**
     * 文本
     */
    public final static int TYPE_TXT = 10;
    /**
     * 图片
     */
    public final static int TYPE_IMG = 11;
    /**
     * 二维码
     */
    public final static int TYPE_QR = 12;
    /**
     * 切纸
     */
    public final static int TYPE_CUT = 13;
    /**
     * 钱箱
     */
    public final static int TYPE_CASHBOX = 14;
    /**
     * 走纸
     */
    public final static int TYPE_FEED = 15;
    /**
     * 条形码
     */
    public final static int TYPE_BARCODE = 16;
    /**
     * 蜂鸣
     */
    public final static int TYPE_BEEP = 18;
    /**
     * 左对齐
     */
    public final static int ALIGN_LEFT = 0;
    /**
     * 剧中对齐
     */
    public final static int ALIGN_CENTER = 1;
    /**
     * 右对齐
     */
    public final static int ALIGN_RIGHT = 2;
    /**
     * 繁体
     */
    public static int LANG_EN = 0;
    /**
     * 简体
     */
    public static int LANG_CN = 1;
    /**
     * png图片
     */
    public static int IMAGE_PNG = 20;
    /**
     * jpg图片
     */
    public static int IMAGE_JPG = 21;
    //字体拉伸模式STRETCH
    public final static int STRETCH_NONE = 0;
    public final static int STRETCH_VERTICAL = 1;
    public final static int STRETCH_HORIZONTAL = 2;
    public final static int STRETCH_VERTICAL_HORIZONTAL = 3;

    public static final int NO_PERMISSION = 1;
    public static final int NO_FIND_DEVICE = 2;
    public static final int CONNECT_ERROR = 3;
    public static final int IO = 4;
    public static final int IP_NOT_BE_NULL = 5;

    /**
     * ESC指令
     */
    public static final int COMMAND_ESC = 1;
    /**
     * 标签指令
     */
    public static final int COMMAND_TSC = 2;
}
