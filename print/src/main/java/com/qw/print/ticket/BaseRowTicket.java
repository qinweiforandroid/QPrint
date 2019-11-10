package com.qw.print.ticket;

import com.qw.print.data.style.Align;
import com.qw.print.data.style.Stretch;

/**
 * Created by qinwei on 2019/4/19 11:21 AM
 */
public abstract class BaseRowTicket {
    protected int type;
    protected boolean isBold;
    protected Stretch stretchType = Stretch.NONE;
    protected Align align = Align.LEFT;

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

    public Stretch getStretchType() {
        return stretchType;
    }


    public Align getAlign() {
        return align;
    }
}
