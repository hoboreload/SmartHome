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

/**
 * Created by bethunej01 on 4/10/17.
 */

public class TodaysWeatherActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Retrieving Your Weather Data...");
        progressDialog.show();

        getWeatherDatabase();
    }

    public void getWeatherDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("Weather");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    getUpdates(dataSnapshot);
                    progressDialog.dismiss();
                }

                else {
                    progressDialog.dismiss();
                    Toast.makeText(TodaysWeatherActivity.this, "Cannot retrieve weather data! Please try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getUpdates(DataSnapshot dataSnapshot) {
        TodaysWeather weather = dataSnapshot.getValue(TodaysWeather.class);

        TextView dayHeading = (TextView) findViewById(R.id.dayHeading);
        dayHeading.setText(weather.getTimestamp());

        TextView cloudsText = (TextView) findViewById(R.id.cloudValue);
        cloudsText.setText(weather.getClouds()+"%");

        TextView currentTempText = (TextView) findViewById(R.id.currentTempValue);
        currentTempText.setText(weather.getCurrentTemp()+"°C");

        TextView humidityText = (TextView) findViewById(R.id.humidityValue);
        humidityText.setText(weather.getHumidity()+"%");

        TextView maxTempText = (TextView) findViewById(R.id.maxTempValue);
        maxTempText.setText(weather.getMaxTemp()+"°C");

        TextView minTempText = (TextView) findViewById(R.id.minTempValue);
        minTempText.setText(weather.getMinTemp()+"°C");

        TextView sunriseText = (TextView) findViewById(R.id.sunriseValue);
        sunriseText.setText(weather.getSunrise());

        TextView sunsetText = (TextView) findViewById(R.id.sunsetValue);
        sunsetText.setText(weather.getSunset());

        TextView weatherText = (TextView) findViewById(R.id.weatherValue);
        String detailText = weather.getWeather() + "- " + weather.getDetail();
        weatherText.setText(detailText);

        TextView windDirectionText = (TextView) findViewById(R.id.windDirectionValue);
        windDirectionText.setText(weather.getWindDirection()+"°");

        TextView windSpeedText = (TextView) findViewById(R.id.windSpeedValue);
        windSpeedText.setText(weather.getWindSpeed()+" m/s");

            ImageView icon = (ImageView) findViewById(R.id.weatherIcon);

            if(weather.getWeather().equals("Clear")) {
                icon.setImageResource(R.mipmap.clear_sky);
            }

            if(weather.getWeather().equals("Clouds")) {
                if(weather.getDetail().equals("few clouds")) {
                    icon.setImageResource(R.mipmap.few_clouds);
                }
                if(weather.getDetail().equals("scattered clouds")) {
                    icon.setImageResource(R.mipmap.scattered_clouds);
                }
                else {
                    icon.setImageResource(R.mipmap.broken_clouds);
                }
            }

            if(weather.getWeather().equals("Rain"))
            {
                if (weather.getDetail().equals("light rain") || (weather.getDetail().equals("moderate rain") || (weather.getDetail().equals("heavy intensity rain") || (weather.getDetail().equals("very heavy rain") || (weather.getDetail().equals("extreme rain")))))) {
                    icon.setImageResource(R.mipmap.rain);
                }
                else if (weather.getDetail().equals("freezing rain")) {
                    icon.setImageResource(R.mipmap.snow);
                }
                else {
                    icon.setImageResource(R.mipmap.shower_rain);
                }
            }

            if(weather.getWeather().equals("Thunderstorm")) {
                icon.setImageResource(R.mipmap.thunderstorm);
            }

            if(weather.getWeather().equals("Drizzle")) {
                icon.setImageResource(R.mipmap.shower_rain);
            }

            if(weather.getWeather().equals("Snow")) {
                icon.setImageResource(R.mipmap.snow);
            }

            if(weather.getWeather().equals("Atmosphere")) {
                icon.setImageResource(R.mipmap.mist);
            }

            if(weather.getWeather().equals("Extreme")) {
                icon.setImageResource(R.mipmap.extreme);
            }

            if(weather.getWeather().equals("Additional")) {
                if((weather.getDetail().equals("calm") || (weather.getDetail().equals("light breeze") || (weather.getDetail().equals("gentle breeze") || (weather.getDetail().equals("moderate breeze") || (weather.getDetail().equals("fresh breeze") || (weather.getDetail().equals("strong breeze")))))))) {
                    icon.setImageResource(R.mipmap.wind);
                }
                else {
                    icon.setImageResource(R.mipmap.extreme);
                }
            }
    }

}

