package com.qw.print.ticket;

import com.qw.print.data.style.Align;

/**
 * Created by qinwei on 2019/4/19 2:05 PM
 * email: qin.wei@mwee.cn
 */
public class LabelRowTicket extends BaseRowTicket {
    private String text;

    public LabelRowTicket(String text) {
        super(RowType.LABEL);
        this.text = text;
    }

    public LabelRowTicket(String text, Align align, boolean isBold) {
        super(RowType.LABEL);
        this.text = text;
        this.align = align;
        this.isBold = isBold;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
