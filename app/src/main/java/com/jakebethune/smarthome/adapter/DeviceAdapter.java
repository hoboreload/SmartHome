package com.jakebethune.smarthome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.activity.DeviceSettingsActivity;
import com.jakebethune.smarthome.activity.HomeActivity;
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
        int offTemp;
        int onTemp;
        String onTime;
        String offTime;
        boolean timeOverride;
        boolean tempOverride;
        String powerState;

        public ViewHolder(View itemView) {
            super(itemView);

            deviceNameText = (TextView) itemView.findViewById(R.id.deviceName);
            devicePowerStateText = (TextView) itemView.findViewById(R.id.deviceStateText);

            RelativeLayout lightNameLayout = (RelativeLayout) itemView.findViewById(R.id.deviceItemRelativeLayout);
            lightNameLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent =  new Intent(v.getContext(), DeviceSettingsActivity.class);
                    intent.putExtra("DEVICE_NAME", deviceNameText.getText().toString());
                    context.startActivity(intent);
                    return true;
                }
            });

            powerButton = (Button) itemView.findViewById(R.id.deviceButton);
            powerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = deviceNameText.getText().toString();
                    nameString = name;
                    String powerState = devicePowerStateText.getText().toString();

                    Query query = databaseReference.child("Device").child(name);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            offTemp = dataSnapshot.getValue(Device.class).getOffTemp();
                            onTemp = dataSnapshot.getValue(Device.class).getOnTemp();
                            onTime = dataSnapshot.getValue(Device.class).getOnTime();
                            offTime = dataSnapshot.getValue(Device.class).getOffTime();
                            tempOverride = dataSnapshot.getValue(Device.class).isTempOverride();
                            timeOverride = dataSnapshot.getValue(Device.class).isTimeOverride();
//                            powerState = dataSnapshot.getValue(Device.class).getPowerState();
                            String powerState = devicePowerStateText.getText().toString();

                            Log.d("TAG", "After: onTemp " + onTemp + ", offTemp " + offTemp + ", onTime " + onTime + ", offTime " + offTime+ ", tempOv " + tempOverride + ", timeOv" + timeOverride);
                            if (powerState == "OFF") {
                                updateDeviceSwitch("1", nameString, onTemp, offTemp, onTime, offTime, timeOverride, tempOverride);
                            }

                            if(powerState == "ON") {
                                updateDeviceSwitch("0", nameString, onTemp, offTemp, onTime, offTime, timeOverride, tempOverride);
                            }


                            Log.d("TAG", "Adapter: onTemp " + onTemp + ", offTemp " + offTemp + ", onTime " + onTime + ", offTime " + offTime+ ", tempOv " + tempOverride + ", timeOv" + timeOverride);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
            });
        }

        private void updateDeviceSwitch(String state, String name, int onTemp, int offTemp, String onTime, String offTime, boolean timeOverride, boolean tempOverride) {

            Log.d("TAG", "Update: onTemp " + onTemp + ", offTemp " + offTemp + ", onTime " + onTime + ", offTime " + offTime+ ", tempOv " + tempOverride + ", timeOv" + timeOverride);


            Device device = new Device();
            device.setPowerState(state);
            device.setDeviceName(name);
            device.setOnTemp(onTemp);
            device.setOffTemp(offTemp);
            device.setOnTime(onTime);
            device.setOffTime(offTime);
            device.setTimeOverride(timeOverride);
            device.setTempOverride(tempOverride);
            Map<String, Object> switchUpdate = new HashMap<String, Object>();
            switchUpdate.put(name, device);
            databaseReference.child("Device").updateChildren(switchUpdate);
        }
    }
}
