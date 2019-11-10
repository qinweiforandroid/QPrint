package com.qw.print.core;

import android.text.TextUtils;

import com.qw.print.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by qinwei on 2019-10-27 21:21
 * email: qinwei_it@163.com
 */
public class NetPrint extends BasePrint {
    private Socket socket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    public NetPrint(PrintRequest request) {
        super(request);
    }

    @Override
    public void checkPrint() throws PrintException {
        if (TextUtils.isEmpty(mRequest.ip)) {
            throw new PrintException(Constants.IP_NOT_BE_NULL, "打印机ip地址为空");
        }
        //check 是否属于同一局域网
    }

    @Override
    public void connect() throws PrintException {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(mRequest.ip, 9100), 5000);
            mInputStream = socket.getInputStream();
            mOutputStream = socket.getOutputStream();
        } catch (IOException e) {
            throw new PrintException(Constants.CONNECT_ERROR, e.getMessage());
        }
    }

    @Override
    public boolean write(byte[] bytes) throws PrintException {
        try {
            mOutputStream.write(bytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new PrintException(Constants.IO, e.getMessage());
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
            socket.close();
            mInputStream = null;
            mOutputStream = null;
            socket = null;
        } catch (IOException e) {
            throw new PrintException(Constants.IO, e.getMessage());
        }
    }
}
