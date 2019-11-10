package com.qw.print.ticket;

import com.qw.print.data.style.Align;
import com.qw.print.data.style.Stretch;

/**
 * 小票标题样式
 * Created by qinwei on 2019/4/19 11:42 AM
 * email: qin.wei@mwee.cn
 */
public class TitleRowTicket extends BaseRowTicket {
    private String title;

    public TitleRowTicket(String title) {
        super(RowType.TITLE);
        this.title = title;
        this.stretchType = Stretch.BOTH;
        this.align = Align.CENTER;
    }

    public String getTitle() {
        return title;
    }
}
