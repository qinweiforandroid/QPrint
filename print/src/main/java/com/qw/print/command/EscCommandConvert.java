package com.qw.print.command;

import com.qw.print.data.PrintItem;

import java.util.ArrayList;

/**
 * Created by qinwei on 2019/4/17 1:56 PM
 * email: qin.wei@mwee.cn
 * @author qinwei
 */
public class EscCommandConvert implements ICommandConvert {

    @Override
    public byte[] convert(ArrayList<PrintItem> items) {
        EscCommand command = new EscCommand();
        command.addInitializePrinter();
        for (PrintItem item : items) {
            switch (item.getType()) {
                case TXT:
                    handlerTxt(command, item);
                    break;
                case CUT:
                    command.addCutAndFeedPaper((byte) 10);
                    break;
                case FEED:
                    command.addPrintAndFeedLines((byte) 2);
                    break;
                case BARCODE:
                    handlerBarCode(command, item);
                    break;
                case IMG:
                    handlerImage(command, item);
                    break;
                case QR:
                    handlerQR(command, item);
                    break;
                default:
                    break;
            }
        }
        return GpUtils.ByteTo_byte(command.getCommand());
    }


    private void handlerQR(EscCommand command, PrintItem item) {
        setAlign(command, item);
        //设置纠错等级
        command.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
        //设置qrcode模块大小
        command.addSelectSizeOfModuleForQRCode((byte) 7);
        //设置qrcode内容
        command.addStoreQRCodeData(item.getText());
        //打印QRCode
        command.addPrintQRCode();
        command.addPrintAndLineFeed();
    }

    private void handlerImage(EscCommand command, PrintItem item) {
        setAlign(command, item);
        //打印图片
        command.addRastBitImage(item.getBitmap(), item.getBitmap().getWidth(), 0);
        command.addPrintAndLineFeed();
    }

    private void handlerBarCode(EscCommand command, PrintItem item) {
        setAlign(command, item);
        //设置条码可识别字符位置在条码下方
        command.addSelectPrintingPositionForHRICharacters(EscCommand.HRI_POSITION.BELOW);
        //设置条码高度为60点
        command.addSetBarcodeHeight((byte) 50);
        //打印Code128码
        command.addCODE128(item.getText());
        command.addPrintAndLineFeed();
    }

    private void setAlign(EscCommand command, PrintItem item) {
        //设置对齐方式指令
        switch (item.getAlign()) {
            case LEFT:
                command.addUserCommand(EscCommand.ALIGN_LEFT);
                break;
            case RIGHT:
                command.addUserCommand(EscCommand.ALIGN_RIGHT);
                break;
            case CENTER:
                command.addUserCommand(EscCommand.ALIGN_CENTER);
                break;
            default:
                break;
        }
    }


    private void handlerTxt(EscCommand command, PrintItem item) {
        setAlign(command, item);
        //设置字体拉伸方式
        switch (item.getStretch()) {
            case NONE:
                command.addUserCommand(EscCommand.NORMAL);
                break;
            case HORIZONTAL:
                command.addUserCommand(EscCommand.DOUBLE_WIDTH);
                break;
            case VERTICAL:
                command.addUserCommand(EscCommand.DOUBLE_HEIGHT);
                break;
            case BOTH:
                command.addUserCommand(EscCommand.DOUBLE_HEIGHT_WIDTH);
                break;
            default:
                break;
        }
        if (item.isBold()) {
            command.addUserCommand(EscCommand.BOLD);
        } else {
            command.addUserCommand(EscCommand.BOLD_CANCEL);
        }
        command.addText(item.getText());
        command.addPrintAndLineFeed();
    }
}