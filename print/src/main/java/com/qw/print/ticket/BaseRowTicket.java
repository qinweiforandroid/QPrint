package com.qw.print.ticket;

import com.qw.print.core.PrintConstants;

/**
 * Created by qinwei on 2019/4/19 11:21 AM
 */
public abstract class BaseRowTicket {
    protected int type;
    protected boolean isBold;
    protected int stretchType = PrintConstants.STRETCH_NONE;
    protected int align = PrintConstants.ALIGN_LEFT;

    public BaseRowTicket(int rowType) {
        this.type = rowType;
        this.isBold = false;
    }

    public int getType() {
        return type;
    }


    public boolean isBold() {
        return isBold;
    }

    public int getStretchType() {
        return stretchType;
    }


    public int getAlign() {
        return align;
    }
}
