package com.qw.print.ticket;

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

    public LabelRowTicket(String text, int align, boolean isBold) {
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
