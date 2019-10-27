package com.qw.print.core;

import android.content.Context;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;


import com.qw.print.PrintContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by qinwei on 2019/1/2 6:28 PM
 * email: qin.wei@mwee.cn
 */
public class UsbPrint extends AbstractPrint {
    private UsbManager usbManager;
    private UsbDevice usbDevice;
    private UsbInterface usbInterface;
    private UsbDeviceConnection connection;
    private UsbEndpoint usbEndpointIn;
    private UsbEndpoint usbEndpointOut;

    public UsbPrint(PrintRequest request) {
        super(request);
    }

    @Override
    public void checkPrint() throws PrintException {
        usbManager = (UsbManager) PrintContext.getContext().getSystemService(Context.USB_SERVICE);
        ArrayList<UsbDevice> usbDevices = (ArrayList<UsbDevice>) getDeviceList();
        for (UsbDevice device : usbDevices) {
            if (TextUtils.equals(device.getDeviceName(), mRequest.deviceId)) {
                usbDevice = device;
                break;
            }
        }
        if (usbDevice == null) {
            throw new PrintException(PrintConstants.NO_FIND_DEVICE, "请检查打印机连接");
        }
        if (!usbManager.hasPermission(usbDevice)) {
            throw new PrintException(PrintConstants.NO_PERMISSION, "请先申请权限");
        }
    }

    @Override
    public void connect() throws PrintException {
        try {
            usbInterface = usbDevice.getInterface(0);
            connection = usbManager.openDevice(usbDevice);
            if (connection.claimInterface(usbInterface, true)) {
                //获取接口上的两个端点，分别对应 OUT 和 IN
                for (int i = 0; i < usbInterface.getEndpointCount(); ++i) {
                    UsbEndpoint end = usbInterface.getEndpoint(i);
                    if (end.getDirection() == UsbConstants.USB_DIR_IN) {
                        usbEndpointIn = end;
                    } else {
                        usbEndpointOut = end;
                    }
                }
            }
        } catch (Exception e) {
            throw new PrintException(PrintConstants.CONNECT_ERROR, e.getMessage());
        }
    }

    @Override
    public boolean write(byte[] bytes) {
        PLog.d("write length:" + bytes.length);
        int result = connection.bulkTransfer(usbEndpointOut, bytes, bytes.length, 500);
        PLog.d("write result:" + result);
        if (result == bytes.length || result == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public byte[] read(int targetLength) throws PrintException {
        try {
            //根据设备实际情况读数据大小
            byte[] receive_bytes = new byte[targetLength];
            int ret = connection.bulkTransfer(usbEndpointIn, receive_bytes, receive_bytes.length, 5000);
            PLog.d("read:" + ret);
            if (ret > 0) {
                PLog.d("read:" + ret + ",receive_bytes[0]:" + receive_bytes[0]);
                return receive_bytes;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new PrintException(e.getMessage());
        }
    }

    @Override
    public void close() throws PrintException {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
            connection.releaseInterface(usbInterface);
            connection = null;
            usbEndpointIn = null;
            usbEndpointOut = null;
            usbManager = null;
            usbInterface = null;
        } catch (Exception e) {
            throw new PrintException(e.getMessage());
        }
    }


    /**
     * 获取 USB 设备列表
     */
    public List<UsbDevice> getDeviceList() {
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        List<UsbDevice> usbDevices = new ArrayList<>();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            usbDevices.add(device);
            PLog.d("get device : " + device.getDeviceName());
        }
        return usbDevices;
    }
}
