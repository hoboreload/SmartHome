package com.jakebethune.smarthome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private static final String TAG = "TAG";
    private Context context;
    private ArrayList<Device> devices;
    private static final String DEVICE = "DEVICE";
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String nameString;
    private String stateString;

    public DeviceAdapter(Context context, ArrayList<Device> devices) {
        this.devices = devices;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_devices, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = devices.get(position).getDeviceName();
        holder.deviceNameText.setText(name);
        String powerState = devices.get(position).getPowerState();

        if(powerState.equals("0")) {
            holder.devicePowerStateText.setText("OFF");
            holder.powerButton.setText("TURN ON");
        }

        if(powerState.equals("1")) {
            holder.devicePowerStateText.setText("ON");
            holder.powerButton.setText("TURN OFF");
        }
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView deviceNameText;
        TextView devicePowerStateText;
        Button powerButton;

        public ViewHolder(View itemView) {
            super(itemView);

            deviceNameText = (TextView) itemView.findViewById(R.id.deviceName);
            devicePowerStateText = (TextView) itemView.findViewById(R.id.deviceStateText);
            RelativeLayout lightNameLayout = (RelativeLayout) itemView.findViewById(R.id.deviceItemRelativeLayout);

            powerButton = (Button) itemView.findViewById(R.id.deviceButton);
            powerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = deviceNameText.getText().toString();
                    nameString = name;
                    String powerState = devicePowerStateText.getText().toString();
                    if(powerState == "OFF") {
                        updateDeviceSwitch("1", nameString);
                    }
                    if(powerState == "ON") {
                        updateDeviceSwitch("0", nameString);
                    }
                }
            });

        }

        private void updateDeviceSwitch(String state, String name) {
            Device device = new Device();
            device.setPowerState(state);
            device.setDeviceName(name);
            Map<String, Object> switchUpdate = new HashMap<String, Object>();
            switchUpdate.put(name, device);
            databaseReference.child("Device").updateChildren(switchUpdate);

            if(state == "0") {
                devicePowerStateText.setText("OFF");
                powerButton.setText("TURN ON");
            }

            if(state == "1") {
                devicePowerStateText.setText("ON");
                powerButton.setText("TURN OFF");
            }
        }
    }
}
