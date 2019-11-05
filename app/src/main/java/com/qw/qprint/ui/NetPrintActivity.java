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
 * Created by qinwei on 2019-10-28 08:53
 * email: qinwei_it@163.com
 */
public class NetPrintActivity extends AppCompatActivity {
    private EditText mNetPrintIpEdt;
    private Button mNetPrintTestBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_print);
        mNetPrintIpEdt = (EditText) findViewById(R.id.mNetPrintIpEdt);
        mNetPrintIpEdt.setText("192.168.31.110");
        mNetPrintTestBtn = (Button) findViewById(R.id.mNetPrintTestBtn);
        mNetPrintTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = mNetPrintIpEdt.getText().toString().trim();
                if (TextUtils.isEmpty(ip)) {
                    ToastUtil.showToast("ip not be null");
                    return;
                }
                TicketPrintUtil.printByNet(ip);
            }
        });
    }
}
