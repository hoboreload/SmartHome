package com.jakebethune.smarthome.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jakebethune.smarthome.model.Device;
import com.jakebethune.smarthome.adapter.DeviceAdapter;
import com.jakebethune.smarthome.R;

import java.util.ArrayList;

public class DeviceActivity extends AppCompatActivity {

    private ArrayList<Device> devices = new ArrayList<>();
    private RecyclerView recyclerView;
    private DeviceAdapter adapter;
    private DatabaseReference databaseReference;
    private EditText deviceNameEditText;
    private Button deviceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.deviceRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        refreshData();

        deviceButton = (Button) findViewById(R.id.deviceButton);

        Button createDeviceButton = (Button) findViewById(R.id.addDeviceButton);
        createDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });
    }

    private void displayDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_device);
        Window window = dialog.getWindow();
        window.setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        dialog.show();

        deviceNameEditText = (EditText) dialog.findViewById(R.id.deviceName);

        Button saveButton = (Button) dialog.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String deviceName = deviceNameEditText.getText().toString();
            if (!TextUtils.isEmpty(deviceName)) {
                String deviceCapital = deviceName.substring(0, 1).toUpperCase() + deviceName.substring(1);
                Query query = databaseReference.child("Device").orderByChild("lightName").equalTo(deviceCapital);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(DeviceActivity.this, "Device name already exists, please use a different name", Toast.LENGTH_LONG).show();
                        }
                        else {
                            String deviceName = deviceNameEditText.getText().toString();
                            String deviceCapital = deviceName.substring(0, 1).toUpperCase() + deviceName.substring(1);
                            String powerState = "0";
                            saveDevice(deviceCapital, powerState, 24, 25);
                            deviceNameEditText.setText("");
                            Toast.makeText(DeviceActivity.this, "Device Added", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Do nothing
                    }
                });
            }
            else {
                Toast.makeText(DeviceActivity.this, "Please enter a device name", Toast.LENGTH_SHORT).show();
            }
            }
        });

        Button cancelButton = (Button) dialog.findViewById(R.id.deviceDialogCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void refreshData() {
        Query query = databaseReference.child("Device");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUpdates(dataSnapshot);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveDevice(String name, String powerState, int minTemp, int maxTemp) {
        Device device = new Device();
        String deviceCapital = name.substring(0, 1).toUpperCase() + name.substring(1);
        device.setDeviceName(deviceCapital);
        device.setPowerState(powerState);
        device.setMinTemp(minTemp);
        device.setMaxTemp(maxTemp);
        databaseReference.child("Device").child(deviceCapital).setValue(device);

    }

    public void getUpdates(DataSnapshot dataSnapshot) {
        devices.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Device device = new Device();
            device.setDeviceName(ds.getValue(Device.class).getDeviceName());
            device.setPowerState(ds.getValue(Device.class).getPowerState());
            device.setMinTemp(ds.getValue(Device.class).getMinTemp());
            device.setMaxTemp(ds.getValue(Device.class).getMaxTemp());
            devices.add(device);
        }

        if (devices.size() >= 0) {
            adapter = new DeviceAdapter(DeviceActivity.this, devices);
            recyclerView.setAdapter(adapter);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

}
