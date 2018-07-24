package com.jakebethune.smarthome;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LightingAdapter extends RecyclerView.Adapter<LightingAdapter.ViewHolder> {

    private static final String TAG = "TAG";
    private Context context;
    private ArrayList<Light> lights;
    private static final String LIGHT = "LIGHT";
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String nameString;
    private String stateString;

    public LightingAdapter(Context context, ArrayList<Light> lights) {
        this.lights = lights;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lighting_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = lights.get(position).getLightName();
        holder.lightNameText.setText(name);
        String powerState = lights.get(position).getPowerState();
        if (powerState == "0") {
            holder.powerState.setChecked(false);
        } else {
            holder.powerState.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return lights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lightNameText;
        Switch powerState;

        public ViewHolder(View itemView) {
            super(itemView);

            lightNameText = (TextView) itemView.findViewById(R.id.lightName);
            powerState = (Switch) itemView.findViewById(R.id.lightSwitch);
            RelativeLayout lightNameLayout = (RelativeLayout) itemView.findViewById(R.id.lightingItemRelativeLayout);
            powerState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = lightNameText.getText().toString();
                    nameString = name;
                    boolean check = powerState.isChecked();
                    if(check) {
                        updateDeviceSwitch("1", nameString);
                    }
                    else {
                        updateDeviceSwitch("0", nameString);
                    }
                }
            });
        }

        private void updateDeviceSwitch(String state, String name) {
            Light light = new Light();
            light.setPowerState(state);
            light.setLightName(name);
            Map<String, Object> switchUpdate = new HashMap<String, Object>();

            switchUpdate.put(name, light);
            databaseReference.child("Light").updateChildren(switchUpdate);
//            refreshSwitch();
        }

// ---------------- RETRIEVES DATA FROM DATABASE BUT STILL NEEDS TO CHANGE SWITCHES ACCORDINGLY ---------------
//        public void refreshSwitch() {
//            Query query = databaseReference.child("Light");
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for  (DataSnapshot datasnap: dataSnapshot.getChildren()) {
//                        String name = datasnap.child("lightName").getValue().toString();
//                        String state = datasnap.child("powerState").getValue().toString();
//                        Log.d(TAG, "NAME IS = " + name + " AND VALUE IS = " + state);
//                        if(state.equals("1")) {
////                            updateDeviceSwitch("1", name);
//                            powerState.setChecked(true);
//                            Log.d(TAG, "THE STATE IS TRUE");
//                        }
//                        if (state.equals("0")) {
//                            powerState.setChecked(false);
////                            updateDeviceSwitch("0", name);
//                            Log.d(TAG, "THE STATE IS FALSE");
//                        }
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
    }
}
