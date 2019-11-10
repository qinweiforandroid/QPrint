package com.qw.print;

/**
 * 打印内容命令类型
 * Created by qinwei on 2019-11-09 09:51
 * email: qinwei_it@163.com
 */
public enum ContentType {
    /**
     * 文本
     */
    TXT,
    /**
     * 图片
     */
    IMG,
    /**
     * 二维码
     */
    QR,
    /**
     * 切纸
     */
    CUT,
    /**
     * 钱箱
     */
    CASHBOX,
    /**
     * 走纸
     */
    FEED,
    /**
     * 条形码
     */
    BARCODE,
    /**
     * 蜂鸣
     */
    BEEP
}
