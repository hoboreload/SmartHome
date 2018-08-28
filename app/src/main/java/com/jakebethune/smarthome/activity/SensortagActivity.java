package com.jakebethune.smarthome.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.Sensortag;
import com.jakebethune.smarthome.adapter.SensortagAdapter;

import java.util.ArrayList;

/**
 * Created by bethunej01 on 4/10/17.
 */

public class SensortagActivity extends AppCompatActivity {

    private ArrayList<Sensortag> sensortag = new ArrayList<>();
    private RecyclerView recyclerView;
    private SensortagAdapter adapter;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensortag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Retrieving Your Sensortag Data...");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.sensortagRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        refreshData();
    }

    public void refreshData() {
        Query query = databaseReference.child("Sensortag");
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

    public void getUpdates(DataSnapshot dataSnapshot) {
        sensortag.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Sensortag sensortagData = new Sensortag();
            sensortagData.setTimestamp(ds.getValue(Sensortag.class).getTimestamp());
            sensortagData.setLight(ds.getValue(Sensortag.class).getLight());
            sensortagData.setObjectTemp(ds.getValue(Sensortag.class).getObjectTemp());
            sensortagData.setAmbientTemp(ds.getValue(Sensortag.class).getAmbientTemp());
            sensortagData.setHumidity(ds.getValue(Sensortag.class).getHumidity());
            sensortagData.setPressure(ds.getValue(Sensortag.class).getPressure());
            sensortag.add(sensortagData);
        }

        if (sensortag.size() >= 0) {
            adapter = new SensortagAdapter(SensortagActivity.this, sensortag);
            recyclerView.setAdapter(adapter);
        }

        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        progressDialog.hide();
    }
}