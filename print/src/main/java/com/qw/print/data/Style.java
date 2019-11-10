package com.qw.print.data;

import com.qw.print.data.style.Align;
import com.qw.print.data.style.Stretch;

/**
 * Created by qinwei on 2019-11-09 14:26
 * email: qinwei_it@163.com
 */
public class Style {
    private Stretch stretch;

    /**
     * 对齐方式
     */
    private Align align;

    /**
     * 是否加粗
     */
    private boolean bold;

    public static class Builder {
        private Stretch stretch = Stretch.NONE;
        /**
         * 对齐方式
         */
        private Align align = Align.LEFT;

        /**
         * 是否加粗
         */
        private boolean bold = false;

        public Builder setAlign(Align align) {
            this.align = align;
            return this;
        }

        public Builder setStretch(Stretch stretch) {
            this.stretch = stretch;
            return this;
        }

        public Builder setBold(boolean bold) {
            this.bold = bold;
            return this;
        }

        public Style build() {
            Style style = new Style();
            style.align = align;
            style.bold = bold;
            style.stretch = stretch;
            return style;
        }
    }

    public Stretch getStretch() {
        return stretch;
    }

    public Align getAlign() {
        return align;
    }

    public boolean isBold() {
        return bold;
    }
}
