package com.qw.print.ticket;

/**
 * Created by qinwei on 2019/4/19 1:39 PM
 */
public class TwoColumnRowTicket extends BaseRowTicket {
    private String text1;
    private String text2;

    public TwoColumnRowTicket(String text1, String text2) {
        super(RowType.TWO_COLUMN);
        this.text1 = text1;
        this.text2 = text2;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
