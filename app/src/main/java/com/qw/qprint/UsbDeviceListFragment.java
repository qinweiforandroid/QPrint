package com.qw.qprint;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.qw.framework.utils.Trace;
import com.qw.framework.widget.pull.BaseViewHolder;
import com.qw.framework.widget.pull.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by qinwei on 2019/1/2 2:47 PM
 * email: qin.wei@mwee.cn
 */

public class UsbDeviceListFragment extends BaseListFragment<UsbDevice> {
    @Override
    protected void initData() {
        setHasOptionsMenu(true);
        mPullRecyclerView.setRefreshing();
    }

    private void loadData() {
        List<UsbDevice> deviceList = getDeviceList();
        if (deviceList != null && deviceList.size() > 0) {
            modules.clear();
            modules.addAll(deviceList);
            adapter.notifyDataSetChanged();
        }
        mPullRecyclerView.onRefreshCompleted();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_print, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_print:
                TicketPrintUtil.printTest1();
                break;
            case R.id.action_ticket:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh(int mode) {
        super.onRefresh(mode);
        if (mode == PullRecyclerView.MODE_PULL_TO_START) {
            loadData();
        }
    }

    @Override
    protected BaseViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    class Holder extends BaseViewHolder implements View.OnClickListener {

        private TextView label;
        private UsbDevice model;

        public Holder(View v) {
            super(v);
            label = (TextView) v;
            v.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void bindData(int i) {
            model = modules.get(i);
            label.setText(model.getDeviceName() + " -- " + model.getDeviceName());
            label.setText("设备名：" + model.getDeviceName() +
                    "\t\t制造商：" + model.getManufacturerName() +
                    "\t\t产品名：" + model.getProductName() +
                    "\n序列号：" + model.getSerialNumber() +
                    "\t\t厂家ID（V_ID）:" + model.getVendorId() +
                    "\t\t产品ID（P_ID）：" + model.getProductId() +
                    "\t\t是否拥有权限：" + hasPermission(model)
            );
        }

        @Override
        public void onClick(View v) {
            if (!hasPermission(model)) {
                requestPermission(model);
            } else {
                PrefsAccessor.getInstance(getContext()).saveString(Constants.KEY_PRINT_DEVICE_NAME, model.getDeviceName());
            }
        }
    }

    private USBReceiver receiver = null;
    private PendingIntent mPermissionIntent = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        receiver = new USBReceiver();
        mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(USBReceiver.ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(USBReceiver.ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        context.registerReceiver(receiver, filter);
    }

    @Override
    public void onDetach() {
        getContext().unregisterReceiver(receiver);
        super.onDetach();
    }

    public class USBReceiver extends BroadcastReceiver {
        public static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                // 获取权限结果的广播
                UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (device != null) {
                    //call method to set up device communication
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        Log.e("USBReceiver", "获取权限成功：" + device.getDeviceName());
                        PrefsAccessor.getInstance(getContext()).saveString(Constants.KEY_PRINT_DEVICE_NAME, device.getDeviceName());
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e("USBReceiver", "获取权限失败：" + device.getDeviceName());
                    }
                }
            } else if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                // 有新的设备插入了，在这里一般会判断这个设备是不是我们想要的，是的话就去请求权限
                ToastUtil.showToast("有新的设备插入了");
                UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (!hasPermission(device)) {
                    requestPermission(device);
                }
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                // 有设备拔出了
                ToastUtil.showToast("有设备拔出了");
            }
        }
    }

    public List<UsbDevice> getDeviceList() {
        UsbManager manager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        List<UsbDevice> usbDevices = new ArrayList<>();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            usbDevices.add(device);
            Trace.d("getDeviceList: " + device.getDeviceName());
        }
        return usbDevices;
    }

    /**
     * mVendorId=1137,mProductId=85  佳博 3150T 标签打印机
     *
     * @param vendorId  厂商ID
     * @param productId 产品ID
     * @return device
     */
    public UsbDevice getUsbDevice(int vendorId, int productId) {
        UsbManager manager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            if (device.getVendorId() == vendorId && device.getProductId() == productId) {
                Log.e("USBUtil", "getDeviceList: " + device.getDeviceName());
                return device;
            }
        }
        ToastUtil.showToast("没有对应的设备");
        return null;
    }

    /**
     * 判断对应 USB 设备是否有权限
     */
    public boolean hasPermission(UsbDevice device) {
        return getUsbManager().hasPermission(device);
    }

    private UsbManager getUsbManager() {
        return (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
    }

    /**
     * 请求获取指定 USB 设备的权限
     */
    public void requestPermission(UsbDevice device) {
        UsbManager usbManager = getUsbManager();
        if (device != null) {
            if (usbManager.hasPermission(device)) {
                ToastUtil.showToast("已经获取到权限");
            } else {
                if (mPermissionIntent != null) {
                    usbManager.requestPermission(device, mPermissionIntent);
                    Toast.makeText(getContext(), "请求USB权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "请注册USB广播", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
