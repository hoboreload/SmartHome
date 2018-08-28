package com.jakebethune.smarthome.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.TodaysWeather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bethunej01 on 4/10/17.
 */

public class TodaysWeatherActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Retrieving Your Weather Data...");
        mProgressDialog.show();

        TextView dayHeading = (TextView) findViewById(R.id.dayHeading);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String newDate = dateFormat.format(date);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        String newDay = simpleDateformat.format(date);
        dayHeading.setText(newDay + " - " + newDate);

        getWeatherDatabase();
    }

    public void getWeatherDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("Weather").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    getUpdates(dataSnapshot);
                    mProgressDialog.dismiss();
                }

                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(TodaysWeatherActivity.this, "Cannot retrieve weather data! Please try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getUpdates(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String clouds = String.valueOf(ds.getValue(TodaysWeather.class).getClouds())+"%";
            String currentTemp = String.valueOf(ds.getValue(TodaysWeather.class).getCurrentTemp())+"°C";
            String detail = ds.getValue(TodaysWeather.class).getDetail();
            String humidity = String.valueOf(ds.getValue(TodaysWeather.class).getHumidity())+"%";
            String maxTemp = String.valueOf(ds.getValue(TodaysWeather.class).getMaxTemp())+"°C";
            String minTemp = String.valueOf(ds.getValue(TodaysWeather.class).getMinTemp())+"°C";
            String sunrise = ds.getValue(TodaysWeather.class).getSunrise();
            String sunset = ds.getValue(TodaysWeather.class).getSunset();
            String weather = ds.getValue(TodaysWeather.class).getWeather();
            String windDirection = String.valueOf(ds.getValue(TodaysWeather.class).getWindDirection())+"°";
            String windSpeed = String.valueOf(ds.getValue(TodaysWeather.class).getWindSpeed())+" m/s";

            TextView cloudsText = (TextView) findViewById(R.id.cloudValue);
            cloudsText.setText(clouds);

            TextView currentTempText = (TextView) findViewById(R.id.currentTempValue);
            currentTempText.setText(currentTemp);

            TextView detailText = (TextView) findViewById(R.id.detailValue);
            detailText.setText(detail);

            TextView humidityText = (TextView) findViewById(R.id.humidityValue);
            humidityText.setText(humidity);

            TextView maxTempText = (TextView) findViewById(R.id.maxTempValue);
            maxTempText.setText(maxTemp);

            TextView minTempText = (TextView) findViewById(R.id.minTempValue);
            minTempText.setText(minTemp);

            TextView sunriseText = (TextView) findViewById(R.id.sunriseValue);
            sunriseText.setText(sunrise);

            TextView sunsetText = (TextView) findViewById(R.id.sunsetValue);
            sunsetText.setText(sunset);

            TextView weatherText = (TextView) findViewById(R.id.weatherValue);
            weatherText.setText(weather);

            TextView windDirectionText = (TextView) findViewById(R.id.windDirectionValue);
            windDirectionText.setText(windDirection);

            TextView windSpeedText = (TextView) findViewById(R.id.windSpeedValue);
            windSpeedText.setText(windSpeed);

            ImageView icon = (ImageView) findViewById(R.id.weatherIcon);

            if(weather.equals("Clear")) {
                icon.setImageResource(R.mipmap.clear_sky);
            }

            if(weather.equals("Clouds")) {
                if(detail.equals("few clouds")) {
                    icon.setImageResource(R.mipmap.few_clouds);
                }
                if(detail.equals("scattered clouds")) {
                    icon.setImageResource(R.mipmap.scattered_clouds);
                }
                else {
                    icon.setImageResource(R.mipmap.broken_clouds);
                }
            }

            if(weather.equals("Rain"))
            {
                if (detail.equals("light rain") || (detail.equals("moderate rain") || (detail.equals("heavy intensity rain") || (detail.equals("very heavy rain") || (detail.equals("extreme rain")))))) {
                    icon.setImageResource(R.mipmap.rain);
                }
                else if (detail.equals("freezing rain")) {
                    icon.setImageResource(R.mipmap.snow);
                }
                else {
                    icon.setImageResource(R.mipmap.shower_rain);
                }
            }

            if(weather.equals("Thunderstorm")) {
                icon.setImageResource(R.mipmap.thunderstorm);
            }

            if(weather.equals("Drizzle")) {
                icon.setImageResource(R.mipmap.shower_rain);
            }

            if(weather.equals("Snow")) {
                icon.setImageResource(R.mipmap.snow);
            }

            if(weather.equals("Atmosphere")) {
                icon.setImageResource(R.mipmap.mist);
            }

            if(weather.equals("Extreme")) {
                icon.setImageResource(R.mipmap.extreme);
            }

            if(weather.equals("Additional")) {
                if((detail.equals("calm") || (detail.equals("light breeze") || (detail.equals("gentle breeze") || (detail.equals("moderate breeze") || (detail.equals("fresh breeze") || (detail.equals("strong breeze")))))))) {
                    icon.setImageResource(R.mipmap.wind);
                }
                else {
                    icon.setImageResource(R.mipmap.extreme);
                }
            }
        }
    }

}

