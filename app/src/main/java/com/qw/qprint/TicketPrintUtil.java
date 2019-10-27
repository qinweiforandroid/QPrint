package com.qw.qprint;

import android.graphics.BitmapFactory;

import com.qw.print.PrintManager;
import com.qw.print.command.PrintUtils;
import com.qw.print.core.PrintConstants;
import com.qw.print.core.PrintItem;
import com.qw.print.core.PrintRequest;
import com.qw.print.core.PrintType;
import com.qw.print.ticket.TitleRowTicket;

/**
 * Created by qinwei on 2019/1/21 1:36 PM
 * email: qin.wei@mwee.cn
 */

public class TicketPrintUtil {
    public static void printTest1() {
        PrintRequest request = new PrintRequest();
        request.type = PrintType.USB;
        request.setCommandType(PrintConstants.COMMAND_ESC);
        request.deviceId = PrefsAccessor.getInstance(MyApplication.getInstance()).getString(Constants.KEY_PRINT_DEVICE_NAME);
        request.addRowTicket(new TitleRowTicket("美味不用等共享餐厅"));
        PrintItem item = PrintItem.create();
        item.setText("打印信息（双倍高）");
        item.setAlign(PrintConstants.ALIGN_LEFT);
        item.setStretchType(PrintConstants.STRETCH_VERTICAL);
        request.addPrintItem(item);

        PrintItem item1 = PrintItem.create();
        item1.setText("打印信息（双倍宽高）");
        item1.setAlign(PrintConstants.ALIGN_LEFT);
        item1.setStretchType(PrintConstants.STRETCH_VERTICAL_HORIZONTAL);
        request.addPrintItem(item1);

        PrintItem item2 = PrintItem.create();
        item2.setText("打印信息（双倍宽）");
        item2.setAlign(PrintConstants.ALIGN_LEFT);
        item2.setStretchType(PrintConstants.STRETCH_HORIZONTAL);
        request.addPrintItem(item2);

        PrintItem itemStyle3 = PrintItem.create();
        itemStyle3.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle3.setText("普通样式左对其显示");
        itemStyle3.setAlign(PrintConstants.ALIGN_LEFT);
        request.addPrintItem(itemStyle3);

        PrintItem itemStyle1 = PrintItem.create();
        itemStyle1.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle1.setText("普通样式居中显示");
        itemStyle1.setAlign(PrintConstants.ALIGN_CENTER);
        request.addPrintItem(itemStyle1);

        PrintItem itemStyle2 = PrintItem.create();
        itemStyle2.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle2.setText("普通样式右对其显示");
        itemStyle2.setAlign(PrintConstants.ALIGN_RIGHT);
        request.addPrintItem(itemStyle2);

        PrintItem itemStyle4 = PrintItem.create();
        itemStyle4.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle4.setBold(true);
        itemStyle4.setText("字体加粗");
        itemStyle4.setAlign(PrintConstants.ALIGN_LEFT);
        request.addPrintItem(itemStyle4);

        //两行左右
        PrintItem itemStyle5 = PrintItem.create();
        itemStyle5.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle5.setText(PrintUtils.format("牌号:19", "单号:201902020001"));
        itemStyle5.setAlign(PrintConstants.ALIGN_LEFT);
        request.addPrintItem(itemStyle5);

        request.addPrintItem(PrintUtils.getLabel("打印3列内容"));

        PrintItem itemStyle6 = PrintItem.create();
        itemStyle6.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle6.setText(PrintUtils.getSingleLine());
        itemStyle6.setAlign(PrintConstants.ALIGN_LEFT);
        request.addPrintItem(itemStyle6);


        PrintItem itemStyle7 = PrintItem.create();
        itemStyle7.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle7.setText(PrintUtils.format("名称", "数量", "总额"));
        itemStyle7.setAlign(PrintConstants.ALIGN_LEFT);
        request.addPrintItem(itemStyle7);
        for (int i = 0; i < 3; i++) {
            itemStyle7 = PrintItem.create();
            itemStyle7.setStretchType(PrintConstants.STRETCH_NONE);
            itemStyle7.setText(PrintUtils.format("青椒土豆丝", "1", "30"));
            itemStyle7.setAlign(PrintConstants.ALIGN_LEFT);
            request.addPrintItem(itemStyle7);
        }
        request.addPrintItem(itemStyle6);

        request.addPrintItem(PrintUtils.getLabel("打印4列内容"));

        itemStyle7 = PrintItem.create();
        itemStyle7.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle7.setText(PrintUtils.format("名称", "单价", "数量", "总额"));
        itemStyle7.setAlign(PrintConstants.ALIGN_LEFT);
        request.addPrintItem(itemStyle7);


        PrintItem itemStyle8 = PrintItem.create();
        itemStyle8.setStretchType(PrintConstants.STRETCH_NONE);
        itemStyle8.setText(PrintUtils.getDoubleLine());
        itemStyle8.setAlign(PrintConstants.ALIGN_LEFT);
        request.addPrintItem(itemStyle8);
        for (int i = 0; i < 3; i++) {
            itemStyle7 = PrintItem.create();
            itemStyle7.setStretchType(PrintConstants.STRETCH_VERTICAL);
            itemStyle7.setText(PrintUtils.format("青椒土豆丝", "10.5", "2", "21.0"));
            itemStyle7.setAlign(PrintConstants.ALIGN_LEFT);
            request.addPrintItem(itemStyle7);
        }

        request.addPrintItem(itemStyle6);

        request.addPrintItem(PrintUtils.getLabel("打印图片内容"));
        PrintItem printImg = PrintItem.create(BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.mipmap.ic_launcher));
        request.addPrintItem(printImg);

        request.addPrintItem(PrintUtils.getLabel("打印二维码内容"));
        PrintItem qrCode = PrintItem.create();
        qrCode.setQRCode("http://www.jd.com");
        qrCode.setAlign(PrintConstants.ALIGN_CENTER);
        request.addPrintItem(qrCode);

//        request.addPrintItem(getLabel("打印条形码内容"));
//        PrintItem barCode = PrintItem.create();
//        barCode.setBarCode("1234567890");
//        barCode.setAlign(PrintConstants.ALIGN_CENTER);
//        request.addPrintItem(barCode);
        request.addPrintItem(PrintItem.createCutPager());
        PrintManager.getInstance().addPrint(request);
    }

}