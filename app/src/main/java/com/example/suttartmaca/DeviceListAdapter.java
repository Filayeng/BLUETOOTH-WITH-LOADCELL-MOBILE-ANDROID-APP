package com.example.suttartmaca;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Object> deviceList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView textAddress;
        TextView textName;

        public ViewHolder(View v) {
            super(v);
            this.textName = (TextView) v.findViewById(R.id.textViewDeviceName);
            this.textAddress = (TextView) v.findViewById(R.id.textViewDeviceAddress);
            this.linearLayout = (LinearLayout) v.findViewById(R.id.linearLayoutDeviceInfo);
        }
    }

    public DeviceListAdapter(Context context2, List<Object> deviceList2) {
        this.context = context2;
        this.deviceList = deviceList2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.device_info_layout, parent, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        DeviceInfoModel deviceInfoModel = (DeviceInfoModel) this.deviceList.get(position);
        itemHolder.textName.setText(deviceInfoModel.getDeviceName());
        itemHolder.textAddress.setText(deviceInfoModel.getDeviceHardwareAddress());
        Intent intent = new Intent(this.context, MainActivity.class);
        intent.putExtra("deviceName", "HC-06");
        intent.putExtra("deviceAddress", "00:19:10:09:18:4F");
        intent.putExtra("control", "false");
        this.context.startActivity(intent);
    }

    public int getItemCount() {
        return this.deviceList.size();
    }
}
