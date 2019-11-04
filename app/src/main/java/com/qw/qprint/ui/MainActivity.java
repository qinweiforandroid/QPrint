package com.qw.qprint.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qw.qprint.R;

public class MainActivity extends AppCompatActivity {
    private Button mPrintUsbBtn;
    private Button mPrintNetBtn;
    private Button mPrintBluetoothBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrintUsbBtn = (Button) findViewById(R.id.mPrintUsbBtn);
        mPrintNetBtn = (Button) findViewById(R.id.mPrintNetBtn);
        mPrintBluetoothBtn = (Button) findViewById(R.id.mPrintBluetoothBtn);

        mPrintUsbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UsbPrintActivity.class));
            }
        });
        mPrintNetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
