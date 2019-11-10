package com.qw.qprint;

import android.graphics.BitmapFactory;

import com.qw.print.data.style.Align;
import com.qw.print.PrintManager;
import com.qw.print.data.style.Stretch;
import com.qw.print.data.Style;
import com.qw.print.utils.PrintUtils;
import com.qw.print.Constants;
import com.qw.print.data.PrintItem;
import com.qw.print.core.PrintRequest;
import com.qw.print.core.PrinterType;
import com.qw.print.ticket.TitleRowTicket;

/**
 * Created by qinwei on 2019/1/21 1:36 PM
 * email: qin.wei@mwee.cn
 */

public class TicketPrintUtil {
    public static void printByUsb() {
        PrintRequest request = new PrintRequest();
        request.type = PrinterType.USB;
        buildRequest(request);
        PrintManager.getInstance().addPrint(request);
    }

    public static void printByNet(String ip) {
        PrintRequest request = new PrintRequest();
        request.type = PrinterType.NET;
        request.ip = ip;
        request.setCommandType(Constants.COMMAND_ESC);
        request.addRowTicket(new TitleRowTicket("DINING ROOM"));

        request.addItem(PrintItem.createFeed());

        PrintItem item = PrintItem.createText(PrintUtils.format("Table:19", "Order:201902020001"));
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.NONE)
                .setAlign(Align.LEFT)
                .setBold(true)
                .build());
        request.addItem(item);

        request.addItem(PrintItem.createDoubleLine());

        item = PrintItem.createText(PrintUtils.format("NAME", "NUM", "PRICE"));
        item.setStyle(new Style.Builder()
                .setAlign(Align.LEFT).build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.getSingleLine());
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("Sashimi Platter ", "3", "230"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("  配料A", "", ""));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("  配料B", "", ""));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("Pot Sticker ", "2", "230"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("Fried Chicken Legs (Spicy Hot)", "4", "230"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("Chicken Salad", "2", "23"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.getDoubleLine());
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("", "Subtotal", "100"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("", "tax", "5"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("", "Total", "105"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("PAY TYPE:", "ONLINE"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);


        item = PrintItem.createText(PrintUtils.getSingleLine());
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText("Notes:more cheese、、、");
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.getDoubleLine());
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText("Welcome next time");
        item.setStyle(new Style.Builder()
                .setAlign(Align.CENTER)
                .build());
        request.addItem(item);
//        request.addPrintItem(PrintUtils.getLabel("打印二维码内容"));
//        PrintItem qrCode = PrintItem.create();
//        qrCode.setQRCode("http://www.jd.com");
//        qrCode.setAlign(Align.CENTER);
//        request.addPrintItem(qrCode);

        request.addItem(PrintUtils.getLabel("打印图片内容"));
        item = PrintItem.create(BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.ic_launcher));
        item.setStyle(new Style.Builder()
                .setAlign(Align.CENTER)
                .build());
        request.addItem(item);

        request.addItem(PrintItem.createCutPager());
        PrintManager.getInstance().addPrint(request);
    }


    public static void printByBluetooth(String macAddress) {
        PrintRequest request = new PrintRequest();
        request.type = PrinterType.BLUETOOTH;
        request.macAddress = macAddress;
        buildRequest(request);
        PrintManager.getInstance().addPrint(request);

    }

    private static void buildRequest(PrintRequest request) {
        request.setCommandType(Constants.COMMAND_ESC);
        request.deviceId = PrefsAccessor.getInstance(MyApplication.getInstance()).getString(com.qw.qprint.Constants.KEY_PRINT_DEVICE_NAME);
        request.addRowTicket(new TitleRowTicket("小票标题"));
        PrintItem item = PrintItem.createText("打印信息（双倍高）");
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.VERTICAL)
                .setAlign(Align.LEFT)
                .build());
        request.addItem(item);

        item = PrintItem.createText("打印信息（双倍宽高）");
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.BOTH)
                .setAlign(Align.LEFT)
                .build());
        request.addItem(item);

        item = PrintItem.createText("打印信息（双倍宽）");
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.HORIZONTAL)
                .setAlign(Align.LEFT)
                .build());
        request.addItem(item);

        item = PrintItem.createText("普通样式左对其显示");
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.NONE)
                .setAlign(Align.LEFT)
                .build());
        request.addItem(item);

        item = PrintItem.createText("普通样式居中显示");
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.NONE)
                .setAlign(Align.CENTER)
                .build());
        request.addItem(item);

        item = PrintItem.createText("普通样式右对其显示");
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.NONE)
                .setAlign(Align.RIGHT)
                .build());
        request.addItem(item);

        item = PrintItem.createText("字体加粗");
        item.setStyle(new Style.Builder()
                .setStretch(Stretch.NONE)
                .setAlign(Align.LEFT)
                .setBold(true)
                .build());
        request.addItem(item);

        //两行左右
        item = PrintItem.createText(PrintUtils.format("牌号:19", "单号:201902020001"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);
        request.addItem(PrintUtils.getLabel("打印3列内容"));

        item = PrintItem.createText(PrintUtils.getSingleLine());
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        item = PrintItem.createText(PrintUtils.format("名称", "数量", "总额"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);
        for (int i = 0; i < 3; i++) {
            item = PrintItem.createText(PrintUtils.format("青椒土豆丝", "1", "30"));
            item.setStyle(new Style.Builder().build());
            request.addItem(item);
        }

        request.addItem(PrintUtils.getLabel("打印4列内容"));

        item = PrintItem.createText(PrintUtils.format("名称", "单价", "数量", "总额"));
        item.setStyle(new Style.Builder().build());
        request.addItem(item);


        item = PrintItem.createText(PrintUtils.getDoubleLine());
        item.setStyle(new Style.Builder().build());
        request.addItem(item);
        for (int i = 0; i < 3; i++) {
            item = PrintItem.createText(PrintUtils.format("青椒土豆丝", "10.5", "2", "21.0"));
            item.setStyle(new Style.Builder()
                    .setAlign(Align.LEFT)
                    .setStretch(Stretch.VERTICAL)
                    .build());
            request.addItem(item);
        }

        item = PrintItem.createText(PrintUtils.getSingleLine());
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        request.addItem(PrintUtils.getLabel("打印图片内容"));
        item = PrintItem.create(BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.mipmap.ic_launcher));
        item.setStyle(new Style.Builder()
                .setAlign(Align.CENTER)
                .build());
        request.addItem(item);

        request.addItem(PrintUtils.getLabel("打印二维码内容"));
        item = PrintItem.createQRCode("http://www.jd.com");
        item.setStyle(new Style.Builder().build());
        request.addItem(item);

        request.addItem(PrintUtils.getLabel("打印条形码内容"));
        item = PrintItem.createBarCode("1234567890");
        item.setStyle(new Style.Builder()
                .setAlign(Align.CENTER)
                .build());
        request.addItem(item);
        request.addItem(PrintItem.createCutPager());
    }

}