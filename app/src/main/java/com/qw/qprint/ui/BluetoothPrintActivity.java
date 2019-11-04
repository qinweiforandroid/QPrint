package com.qw.qprint.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qw.qprint.R;
import com.qw.qprint.TicketPrintUtil;
import com.qw.qprint.ToastUtil;

/**
 * Created by qinwei on 2019-11-04 11:11
 * email: qinwei_it@163.com
 */
public class BluetoothPrintActivity extends AppCompatActivity {

    private EditText mBluethoothPrintIpEdt;
    private Button mBluethoothPrintTestBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBluethoothPrintIpEdt = (EditText) findViewById(R.id.mBluethoothPrintIpEdt);
        mBluethoothPrintTestBtn = (Button) findViewById(R.id.mBluethoothPrintTestBtn);
        mBluethoothPrintTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mac = mBluethoothPrintIpEdt.getText().toString().trim();
                if (TextUtils.isEmpty(mac)) {
                    ToastUtil.showToast("mac address not be null");
                    return;
                }
                TicketPrintUtil.printByBluetooth(mac);
            }
        });
    }
}