package com.qw.qprint.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qw.qprint.R;

/**
 * Created by qinwei on 2019-10-28 08:44
 * email: qinwei_it@163.com
 */
public class UsbPrintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb_print);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mContent, new UsbDeviceListFragment()).commit();
        }
    }
}
