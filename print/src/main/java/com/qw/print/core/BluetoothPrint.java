package com.qw.print.core;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by qinwei on 2019-11-04 10:33
 * email: qinwei_it@163.com
 */
public class BluetoothPrint extends AbsPrint {
    private final static String UUID = "00001101-0000-1000-8000-00805f9b34fb";
    private BluetoothDevice mBluetoothDevice;
    private BluetoothSocket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    public BluetoothPrint(PrintRequest request) {
        super(request);
    }

    @Override
    public void checkPrint() throws PrintException {
        List<BluetoothDevice> mDevices = findBluetoothDevices();
        if (mDevices != null) {
            throw new PrintException(PrintConstants.NO_FIND_DEVICE, "请检查打印机");
        }
        try {
            //首先匹配蓝牙的mac地址
            for (BluetoothDevice temp : mDevices) {
                if (!TextUtils.isEmpty(temp.getAddress())) {
                    if (temp.getAddress().equalsIgnoreCase(mRequest.macAddress)) {
                        mBluetoothDevice = temp;
                        return;
                    }
                }
            }
            //然后匹配蓝牙设备的DeviceClass
            for (BluetoothDevice temp : mDevices) {
                if (temp.getBluetoothClass() != null) {
                    if (1536 == temp.getBluetoothClass().getMajorDeviceClass()) {
                        mBluetoothDevice = temp;
                        return;
                    }
                }
            }
            if (mBluetoothDevice == null) {
                throw new PrintException(PrintConstants.NO_FIND_DEVICE, "请检查打印机");
            }
        } catch (Exception e) {
            throw new PrintException(PrintConstants.NO_FIND_DEVICE, "请检查打印机");
        }
    }

    @Override
    public void connect() throws PrintException {
        try {
            java.util.UUID uuid = java.util.UUID.fromString(UUID);
            mSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new PrintException(PrintConstants.CONNECT_ERROR, "connect error");
        }
    }

    @Override
    public boolean write(byte[] bytes) throws PrintException {
        try {
            mOutputStream.write(bytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new PrintException(PrintConstants.IO, e.getMessage());
        }
    }

    @Override
    public byte[] read(int targetLength) throws PrintException {
        return new byte[0];
    }

    @Override
    public void close() throws PrintException {
        try {
            mInputStream.close();
            mOutputStream.close();
            mSocket.close();
            mInputStream = null;
            mOutputStream = null;
            mSocket = null;
        } catch (IOException e) {
            throw new PrintException(PrintConstants.IO, e.getMessage());
        }
    }

    private List<BluetoothDevice> findBluetoothDevices() throws PrintException {
        List<BluetoothDevice> pairedBtDevices = new ArrayList<>();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            throw new PrintException("使用蓝牙打印机前,请开启蓝牙");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            throw new PrintException("使用蓝牙打印机前,请开启蓝牙");
        }

        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        if (bondedDevices != null && !bondedDevices.isEmpty()) {
            pairedBtDevices.addAll(bondedDevices);
        }

        // 联迪C10、C9增加内置打印机
        if (TextUtils.equals(Build.MODEL, "AECR C10") || TextUtils.equals(Build.MODEL, "AECR C9")) {
            pairedBtDevices.add(mBluetoothAdapter.getRemoteDevice("00:01:02:03:0A:0B"));
        }
        return pairedBtDevices;
    }
}