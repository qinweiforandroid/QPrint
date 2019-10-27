package com.qw.print.ticket;

/**
 * Created by qinwei on 2019/4/19 1:39 PM
 * email: qin.wei@mwee.cn
 */
public class ThreeColumnRowTicket extends BaseRowTicket {
    private String text1;
    private String text2;
    private String text3;

    public ThreeColumnRowTicket(String text1, String text2, String text3) {
        super(RowType.THREE_COLUMN);
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
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

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
