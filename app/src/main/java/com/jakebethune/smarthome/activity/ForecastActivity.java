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
import com.jakebethune.smarthome.model.Forecast;
import com.jakebethune.smarthome.adapter.ForecastAdapter;
import com.jakebethune.smarthome.R;


import java.util.ArrayList;

/**
 * Created by bethunej01 on 8/10/17.
 */

//Shows the 3 day forecast of the weather from openweathermap
public class ForecastActivity extends AppCompatActivity {

    private ArrayList<Forecast> forecast = new ArrayList<>();
    private RecyclerView recyclerView;
    private ForecastAdapter adapter;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    //When the page is created show a progress dialog and set the recyclerview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Retrieving Your Weather Forecast...");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.forecastRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        refreshData();
    }


    public void refreshData() {
        Query query = databaseReference.child("5 Day Forecast").limitToLast(5);
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
        forecast.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Forecast forecastWeather = new Forecast();
            forecastWeather.setDay(ds.getValue(Forecast.class).getDay());
            forecastWeather.setClouds(ds.getValue(Forecast.class).getClouds());
            forecastWeather.setHumidity(ds.getValue(Forecast.class).getHumidity());
            forecastWeather.setDescription(ds.getValue(Forecast.class).getDescription());
            forecastWeather.setPressure(ds.getValue(Forecast.class).getPressure());
            forecastWeather.setMaxTemp(ds.getValue(Forecast.class).getMaxTemp());
            forecastWeather.setMinTemp(ds.getValue(Forecast.class).getMinTemp());
            forecastWeather.setWeather(ds.getValue(Forecast.class).getWeather());
            forecastWeather.setWindDirection(ds.getValue(Forecast.class).getWindDirection());
            forecastWeather.setWindSpeed(ds.getValue(Forecast.class).getWindSpeed());
            forecastWeather.setDayTemp(ds.getValue(Forecast.class).getDayTemp());
            forecastWeather.setMorningTemp(ds.getValue(Forecast.class).getMorningTemp());
            forecastWeather.setEveTemp(ds.getValue(Forecast.class).getEveTemp());
            forecastWeather.setNightTemp(ds.getValue(Forecast.class).getNightTemp());
            forecast.add(forecastWeather);
        }

        if (forecast.size() >= 0) {
            adapter = new ForecastAdapter(ForecastActivity.this, forecast);
            recyclerView.setAdapter(adapter);
        }

        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        progressDialog.hide();
    }
}