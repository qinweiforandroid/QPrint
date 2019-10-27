package com.qw.qprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qw.print.PrintConfig;
import com.qw.print.PrintManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.mContent, new UsbDeviceListFragment()).commit();
    }
}
