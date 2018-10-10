package com.jakebethune.smarthome.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.Sensortag;

/**
 * Created by bethunej01 on 4/10/17.
 */

public class SensortagActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

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

        getSensortagDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getSensortagDatabase() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("Sensortag");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    getUpdates(dataSnapshot);
                    progressDialog.dismiss();
                }

                else {
                    progressDialog.dismiss();
                    Toast.makeText(SensortagActivity.this, "Cannot retrieve sensortag data! Please try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getUpdates(DataSnapshot dataSnapshot) {

        Sensortag sensortag = dataSnapshot.getValue(Sensortag.class);

        TextView timestampText = (TextView) findViewById(R.id.timeHeading);
        TextView lightText = (TextView) findViewById(R.id.lightValue);
        TextView objectTempText = (TextView) findViewById(R.id.objectTempValue);
        TextView ambientTempText = (TextView) findViewById(R.id.ambientTempValue);
        TextView humidityText = (TextView) findViewById(R.id.humidityValue);
        TextView pressureText = (TextView) findViewById(R.id.pressureValue);

        timestampText.setText(sensortag.getTimestamp());
        lightText.setText(sensortag.getLight() + " lux");
        ambientTempText.setText(sensortag.getAmbientTemp() + "°C");
        objectTempText.setText(sensortag.getObjectTemp() + "°C");
        humidityText.setText(sensortag.getHumidity() + "%");
        pressureText.setText(sensortag.getPressure() + " hPa");

    }
}