package com.jakebethune.smarthome.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

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
    private TimePicker onTimePicker;
    private TimePicker offTimePicker;
    private Button deleteButton;
    private Button saveButton;
    private boolean tempOverride;
    private boolean timeOverride;
    private Button tempButton;
    private Button timeButton;

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

        refreshData();

        minNumberPicker = (NumberPicker) findViewById(R.id.minTemp_numberPicker);
        minNumberPicker.setMinValue(0);
        minNumberPicker.setMaxValue(50);
        minNumberPicker.setWrapSelectorWheel(false);

        maxNumberPicker = (NumberPicker) findViewById(R.id.maxTemp_numberPicker);
        maxNumberPicker.setMinValue(0);
        maxNumberPicker.setMaxValue(50);
        maxNumberPicker.setWrapSelectorWheel(false);

        onTimePicker = (TimePicker) findViewById(R.id.onTimePicker);
        onTimePicker.is24HourView();
        offTimePicker = (TimePicker) findViewById(R.id.offTimePicker);
        offTimePicker.is24HourView();

        tempButton = (Button) findViewById(R.id.tempButton);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = tempButton.getText().toString();
                if (temp == "Turn On Temp Override") {
                    tempButton.setText("Turn Off Temp Override");
                }

                if (temp == "Turn Off Temp Override") {
                    tempButton.setText("Turn On Temp Override");
                }

                Query query = databaseReference.child("Device").child(deviceName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @TargetApi(23)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String powerState = dataSnapshot.getValue(Device.class).getPowerState();
                        int minTemp = minNumberPicker.getValue();
                        int maxTemp = maxNumberPicker.getValue();
                        String onHour = String.valueOf(onTimePicker.getHour());
                        String onMinute = String.valueOf(onTimePicker.getMinute());
                        String onTime = onHour + ":" + onMinute;
                        String offHour = String.valueOf(offTimePicker.getHour());
                        String offMinute = String.valueOf(offTimePicker.getMinute());
                        String offTime = offHour + ":" + offMinute;
                        boolean timeOver = dataSnapshot.getValue(Device.class).isTimeOverride();

                        if (tempButton.getText().toString() == "Turn On Temp Override") {
                            saveDevice(deviceName, powerState, minTemp, maxTemp, onTime, offTime, false, timeOver);
                        }

                        if (tempButton.getText().toString() == "Turn Off Temp Override") {
                            saveDevice(deviceName, powerState, minTemp, maxTemp, onTime, offTime, true, timeOver);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        timeButton = (Button) findViewById(R.id.timeButton);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeButton.getText().toString();
                if (time == "Turn On Time Override") {
                    timeButton.setText("Turn Off Time Override");
                }

                if (time == "Turn Off Time Override") {
                    timeButton.setText("Turn On Time Override");
                }

                Query query = databaseReference.child("Device").child(deviceName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @TargetApi(23)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String powerState = dataSnapshot.getValue(Device.class).getPowerState();
                        int minTemp = minNumberPicker.getValue();
                        int maxTemp = maxNumberPicker.getValue();
                        String onHour = String.valueOf(onTimePicker.getHour());
                        String onMinute = String.valueOf(onTimePicker.getMinute());
                        String onTime = onHour + ":" + onMinute;
                        String offHour = String.valueOf(offTimePicker.getHour());
                        String offMinute = String.valueOf(offTimePicker.getMinute());
                        String offTime = offHour + ":" + offMinute;
                        boolean tempOver = dataSnapshot.getValue(Device.class).isTempOverride();

                        if (timeButton.getText().toString() == "Turn On Time Override") {
                            saveDevice(deviceName, powerState, minTemp, maxTemp, onTime, offTime, tempOver, false);
                        }

                        if (timeButton.getText().toString() == "Turn Off Time Override") {
                            saveDevice(deviceName, powerState, minTemp, maxTemp, onTime, offTime, tempOver, true);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        saveButton = (Button) findViewById(R.id.saveSettingsButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            @TargetApi(23)
            public void onClick(View v) {
                Query query = databaseReference.child("Device").child(deviceName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String powerState = dataSnapshot.getValue(Device.class).getPowerState();
                        int minTemp = minNumberPicker.getValue();
                        int maxTemp = maxNumberPicker.getValue();
                        String onHour = String.valueOf(onTimePicker.getHour());
                        String onMinute = String.valueOf(onTimePicker.getMinute());
                        String onTime = onHour + ":" + onMinute;
                        String offHour = String.valueOf(offTimePicker.getHour());
                        String offMinute = String.valueOf(offTimePicker.getMinute());
                        String offTime = offHour + ":" + offMinute;
                        boolean timeOver = dataSnapshot.getValue(Device.class).isTimeOverride();
                        boolean tempOver = dataSnapshot.getValue(Device.class).isTempOverride();

                        saveDevice(deviceName, powerState, minTemp, maxTemp, onTime, offTime, tempOver, timeOver);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        deleteButton = (Button) findViewById(R.id.deleteSettingsButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = databaseReference.child("Device").child(deviceName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(getBaseContext(), DeviceActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveDevice(String name, String powerState, int minTemp, int maxTemp, String onTime, String offTime, boolean tempOverride, boolean timeOverride) {
        Device device = new Device();
        device.setDeviceName(name);
        device.setPowerState(powerState);
        device.setOnTemp(minTemp);
        device.setOffTemp(maxTemp);
        device.setOnTime(onTime);
        device.setOffTime(offTime);
        device.setTempOverride(tempOverride);
        device.setTimeOverride(timeOverride);
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

    @TargetApi(23)
    public void getUpdates(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Device device = dataSnapshot.getValue(Device.class);
            maxNumberPicker.setValue(device.getOffTemp());
            minNumberPicker.setValue(device.getOnTemp());
            String timeOnString = device.getOnTime();
            String[] timeOn = timeOnString.split(":");
            onTimePicker.setHour(Integer.parseInt(timeOn[0]));
            onTimePicker.setMinute(Integer.parseInt(timeOn[1]));
            onTimePicker.setIs24HourView(true);
            String timeOffString = device.getOffTime();
            String[] timeOff = timeOffString.split(":");
            offTimePicker.setHour(Integer.parseInt(timeOff[0]));
            offTimePicker.setMinute(Integer.parseInt(timeOff[1]));
            offTimePicker.setIs24HourView(true);
            tempOverride = (device.isTempOverride());
            timeOverride = (device.isTimeOverride());

            if (tempOverride == true) {
                tempButton.setText("Turn Off Temp Override");
            }

            if (tempOverride == false) {
                tempButton.setText("Turn On Temp Override");
            }

            if (timeOverride == true) {
                timeButton.setText("Turn Off Time Override");
            }

            if (timeOverride == false) {
                timeButton.setText("Turn On Time Override");
            }

        }
    }
}
