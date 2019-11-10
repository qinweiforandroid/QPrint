package com.qw.print.data;

import com.qw.print.ContentType;

/**
 * Created by qinwei on 2019-11-09 15:28
 * email: qinwei_it@163.com
 */
public class TextItem extends BaseItem {
    public TextItem() {
        type = ContentType.TXT;
    }

    /**
    /**
     * 样式描述
     */
    private Style style;
}
