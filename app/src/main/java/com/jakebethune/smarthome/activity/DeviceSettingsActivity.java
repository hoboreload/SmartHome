package com.jakebethune.smarthome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.Device;

import java.util.HashMap;
import java.util.Map;

public class DeviceSettingsActivity extends AppCompatActivity {

    private String deviceName;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private NumberPicker minNumberPicker;
    private NumberPicker maxNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        deviceName = intent.getStringExtra("DEVICE_NAME");
        TextView heading = (TextView) findViewById(R.id.heading_settings);
        heading.setText(deviceName);

        minNumberPicker = (NumberPicker) findViewById(R.id.minTemp_numberPicker);
        minNumberPicker.setMinValue(0);
        minNumberPicker.setMaxValue(50);
        minNumberPicker.setWrapSelectorWheel(false);

        maxNumberPicker = (NumberPicker) findViewById(R.id.maxTemp_numberPicker);
        maxNumberPicker.setMinValue(0);
        maxNumberPicker.setMaxValue(50);
        maxNumberPicker.setWrapSelectorWheel(false);

        refreshData();

        Button button = (Button) findViewById(R.id.tempValue_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = databaseReference.child("Device").child(deviceName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String powerState = dataSnapshot.getValue(Device.class).getPowerState();
                        int minTemp = minNumberPicker.getValue();
                        int maxTemp = maxNumberPicker.getValue();
                        saveDevice(deviceName, powerState, minTemp, maxTemp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    private void saveDevice(String name, String powerState, int minTemp, int maxTemp) {
        Device device = new Device();
        device.setDeviceName(name);
        device.setPowerState(powerState);
        device.setMinTemp(minTemp);
        device.setMaxTemp(maxTemp);
        Map<String, Object> switchUpdate = new HashMap<String, Object>();
        switchUpdate.put(name, device);
        databaseReference.child("Device").updateChildren(switchUpdate);
    }


    public void refreshData() {
        Query query = databaseReference.child("Device").child(deviceName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getUpdates(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Device device = dataSnapshot.getValue(Device.class);
            maxNumberPicker.setValue(device.getMaxTemp());
            minNumberPicker.setValue(device.getMinTemp());
        }
    }
}
