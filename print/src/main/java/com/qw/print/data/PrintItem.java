package com.qw.print.data;

import android.graphics.Bitmap;

import com.qw.print.ContentType;
import com.qw.print.data.style.Align;
import com.qw.print.data.style.Stretch;
import com.qw.print.utils.PrintUtils;

/**
 * Created by qinwei on 2019/4/17 10:53 AM
 * email: qin.wei@mwee.cn
 */
public class PrintItem {
    /**
     * 打印文字
     */
    private String text;

    /**
     * 打印图片
     */
    private Bitmap bitmap;

    /**
     * 打印指令内容类型
     */
    private ContentType type;

    /**
     * 行数据样式描述
     */
    private Style style;

    public static PrintItem createText(String content) {
        PrintItem item = new PrintItem();
        item.type = ContentType.TXT;
        item.text = content;
        return item;
    }

    public static PrintItem createFeed() {
        PrintItem item = new PrintItem();
        item.type = ContentType.FEED;
        return item;
    }

    public static PrintItem create(Bitmap bitmap) {
        PrintItem item = new PrintItem();
        item.type = ContentType.IMG;
        item.bitmap = bitmap;
        return item;
    }

    /**
     * 创建一个切纸数据模型
     *
     * @return
     */
    public static PrintItem createCutPager() {
        PrintItem printItem = new PrintItem();
        printItem.type = ContentType.CUT;
        return printItem;
    }

    public static PrintItem createOpenCashbox() {
        PrintItem printItem = new PrintItem();
        printItem.type = ContentType.CASHBOX;
        return printItem;
    }

    public static PrintItem createQRCode(String content) {
        PrintItem printItem = new PrintItem();
        printItem.type = ContentType.QR;
        printItem.text = content;
        return printItem;
    }

    public static PrintItem createBarCode(String content) {
        PrintItem printItem = new PrintItem();
        printItem.type = ContentType.BARCODE;
        printItem.text = content;
        return printItem;
    }

    public static PrintItem createDoubleLine() {
        PrintItem text = createText(PrintUtils.getDoubleLine());
        text.setStyle(new Style.Builder()
                .setAlign(Align.LEFT)
                .build());
        return text;

    }


    public Bitmap getBitmap() {
        return bitmap;
    }


    public String getText() {
        return text;
    }

    public ContentType getType() {
        return type;
    }


    public Align getAlign() {
        return getStyle().getAlign();
    }


    public Stretch getStretch() {
        return getStyle().getStretch();
    }

    public boolean isBold() {
        return getStyle().isBold();
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
