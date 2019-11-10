package com.qw.print.data;

import android.graphics.Bitmap;

import com.qw.print.ContentType;

/**
 * Created by qinwei on 2019-11-09 15:30
 * email: qinwei_it@163.com
 */
public class ImgItem extends BaseItem {
    public ImgItem() {
        type = ContentType.IMG;
    }

    /**
     * 图片内容
     */
    private Bitmap content;
    /**
     * 样式描述
     */
    private Style style;

    public Bitmap getContent() {
        return content;
    }

    public void setContent(Bitmap content) {
        this.content = content;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
