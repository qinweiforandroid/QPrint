package com.qw.print.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.qw.print.command.PrintUtils;
import com.qw.print.ticket.BaseRowTicket;

import java.util.ArrayList;

/**
 * Created by qinwei on 2019/1/3 10:13 AM
 * email: qin.wei@mwee.cn
 */

public class PrintTask implements Runnable {
    private PrintRequest request;
    private IPrint print;
    private OnPrintTaskListener listener;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -1:
                    PrintException exception = (PrintException) msg.obj;
                    exception.printStackTrace();
                    break;
                case 200:
                    break;
                default:
                    break;
            }
        }
    };

    public PrintTask(PrintRequest request) {
        this.request = request;
        PLog.d("打印机类型:" + request.type.name());
        switch (request.type) {
            case BLUETOOTH:
                PLog.d("蓝牙mac地址:" + request.macAddress);
                break;
            case NET:
                PLog.d("网口ip地址:" + request.ip);
                break;
            case USB:
                PLog.d("usb device id:" + request.deviceId);
                print = new UsbPrint(request);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        try {
            listener.onStart(request.tag);
            PLog.d("检查打印机状态");
            print.checkPrint();
            PLog.d("打开连接..");
            print.connect();
//                checkPrintStatus();
            ArrayList<BaseRowTicket> rowTickets = request.getRowTickets();
            if (rowTickets.size() > 0) {
                request.getPrintItems().addAll(0, PrintUtils.convertPrintItem(rowTickets));
            }
            PLog.d("指令转换..");
            ICommandConvert convert = CommandConvertFactory.create(request.getCommandType());
            byte[] bytes = convert.convert(request.getPrintItems());
            PLog.d("指令长度:" + bytes.length);
            PLog.d("开始发送数据..");
            boolean isSuccess = print.write(bytes);
            Message msg = new Message();
            if (isSuccess) {
                PLog.d("打印成功..");
                byte[] read = print.read(7);
                msg.what = 200;
            } else {
                msg.what = -1;
                msg.obj = new PrintException("打印失败");
            }
            PLog.d("关闭连接..");
            print.close();
            mHandler.sendMessage(msg);
        } catch (PrintException e) {
            PLog.d("打印失败:" + e.getMessage());
            Message msg = Message.obtain();
            msg.what = -1;
            msg.obj = e;
            mHandler.sendMessage(msg);
        }
        listener.onCompleted(request.tag);
    }

    private void checkPrintStatus() throws PrintException {
        //检查打印机状态
        byte[] tcmd = new byte[3];
        tcmd[0] = 0x10;
        tcmd[1] = 0x04;
        tcmd[2] = 0x3;
        PLog.d("检查打印机状态..");
        print.write(tcmd);
        //获取打印机检测结果
        byte[] checkResult = print.read(7);
        PLog.d("获取打印机检测结果..");
        if (checkResult != null && checkResult.length > 0) {
            int value = checkResult[0];
            if ((value & 12) == 12) {
                PLog.d("纸将用完");
            } else if ((value & 96) == 96) {
                PLog.d("纸已用完");
            } else if ((value & 4) == 4) {
                PLog.d("开盖");
            } else {
                PLog.d("有纸");
            }
        }
    }

    public void setOnPrintTaskListener(OnPrintTaskListener listener) {
        this.listener = listener;
    }


    public interface OnPrintTaskListener {
        void onStart(String tag);

        void onCompleted(String tag);
    }


}