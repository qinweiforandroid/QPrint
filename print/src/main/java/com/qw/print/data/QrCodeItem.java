package com.qw.print.data;

import com.qw.print.ContentType;

/**
 * Created by qinwei on 2019-11-09 15:32
 * email: qinwei_it@163.com
 */
public class QrCodeItem extends BaseItem {
    public QrCodeItem() {
        type = ContentType.QR;
    }

    /**
     * 二维码内容
     */
    private String content;
    /**
     * 样式描述
     */
    private Style style;


    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
