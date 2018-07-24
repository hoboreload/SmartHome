package com.jakebethune.smarthome;

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
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LightingActivity extends AppCompatActivity {

    private ArrayList<Light> lights = new ArrayList<>();
    private RecyclerView recyclerView;
    private LightingAdapter adapter;
    private DatabaseReference databaseReference;
    private EditText deviceNameEditText;
    private Switch deviceSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.lightRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        refreshData();

        deviceSwitch = (Switch) findViewById(R.id.lightSwitch);

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
        dialog.setContentView(R.layout.device_dialog);
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
                    Query query = databaseReference.child("Light").orderByChild("lightName").equalTo(deviceCapital);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(LightingActivity.this, "Device name already exists, please use a different name", Toast.LENGTH_LONG).show();
                            } else {
                                String deviceName = deviceNameEditText.getText().toString();
                                String deviceCapital = deviceName.substring(0, 1).toUpperCase() + deviceName.substring(1);
                                String powerState = "0";
                                saveDevice(deviceCapital, powerState);
                                deviceNameEditText.setText("");
                                Toast.makeText(LightingActivity.this, "Device Added", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //Do nothing
                        }
                    });
                } else {
                    Toast.makeText(LightingActivity.this, "Please enter a device name", Toast.LENGTH_SHORT).show();
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
//        Query query = databaseReference.child("Light").orderByChild("lightName");
        Query query = databaseReference.child("Light");
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

    private void saveDevice(String name, String powerState) {
        Light light = new Light();
        String deviceCapital = name.substring(0, 1).toUpperCase() + name.substring(1);
        light.setLightName(deviceCapital);
        light.setPowerState(powerState);
//        databaseReference.child("Light").push().setValue(light);

        databaseReference.child("Light").child(deviceCapital).setValue(light);

    }

    public void getUpdates(DataSnapshot dataSnapshot) {
        lights.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Light light = new Light();
            light.setLightName(ds.getValue(Light.class).getLightName());
            light.setPowerState(ds.getValue(Light.class).getPowerState());
            lights.add(light);
        }

        if (lights.size() >= 0) {
            adapter = new LightingAdapter(LightingActivity.this, lights);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

}
