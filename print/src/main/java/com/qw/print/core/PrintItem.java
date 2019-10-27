package com.qw.print.core;

import android.graphics.Bitmap;

/**
 * Created by qinwei on 2019/4/17 10:53 AM
 * email: qin.wei@mwee.cn
 */
public class PrintItem {
    /**
     * 主流的尺寸
     * 80mm 48
     * 76mm CPL是42/35
     * 76mm CPL40/33
     * 58mm 32
     */
    private int pageSize = 48;
    /**
     * gbk字符占用的宽度，默认是一个全角是2个字符，有些打印机是1.5个字符
     */
    private float charSize = 2;

    /**
     * 打印文字
     */
    private String text;

    /**
     * 打印图片
     */
    private Bitmap bitmap;
    /**
     * 指令类型
     */
    private int type;
    /**
     * 拉伸枚举
     * 0：无拉伸 1：垂直拉伸 2：横向拉伸
     */
    private int stretchType;

    /**
     * 对齐方式{@link PrintConstants}
     */
    private int align;

    private boolean isBold;

    public static PrintItem create() {
        return new PrintItem();
    }

    public static PrintItem create(Bitmap bitmap) {
        PrintItem item = new PrintItem();
        item.setBitmap(bitmap);
        item.setAlign(PrintConstants.ALIGN_CENTER);
        return item;
    }

    /**
     * 创建一个切纸数据模型
     *
     * @return
     */
    public static PrintItem createCutPager() {
        PrintItem printItem = new PrintItem();
        printItem.type = PrintConstants.TYPE_CUT;
        return printItem;
    }

    public static PrintItem createOpenCashbox() {
        PrintItem printItem = new PrintItem();
        printItem.type = PrintConstants.TYPE_CASHBOX;
        return printItem;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        type = PrintConstants.TYPE_IMG;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public float getCharSize() {
        return charSize;
    }

    public void setCharSize(float charSize) {
        this.charSize = charSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        type = PrintConstants.TYPE_TXT;
    }


    public void setQRCode(String content) {
        this.text = content;
        type = PrintConstants.TYPE_QR;
    }

    public void setBarCode(String content) {
        this.text = content;
        type = PrintConstants.TYPE_BARCODE;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public int getStretchType() {
        return stretchType;
    }

    public void setStretchType(int stretchType) {
        this.stretchType = stretchType;
    }


}
