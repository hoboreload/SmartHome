package com.jakebethune.smarthome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jakebethune.smarthome.adapter.HomeAdapter;
import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.TodaysWeather;

/**
 * Created by bethunej01 on 26/2/18.
 */

public class HomeActivity extends AppCompatActivity {

    GridView gridview;
    String[] gridviewText = {"Home", "Devices", "Weather", "SensorTag"};
    int[] gridviewImage = {R.drawable.home, R.drawable.devices, R.drawable.weather, R.drawable.weather};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        HomeAdapter adapterViewAndroid = new HomeAdapter(HomeActivity.this, gridviewText, gridviewImage);
        gridview = (GridView) findViewById(R.id.menu_gridview);
        gridview.setAdapter(adapterViewAndroid);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent;
                switch(position) {
                    case 0:
                        intent =  new Intent(view.getContext(), HomeActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent =  new Intent(view.getContext(), DeviceActivity.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent =  new Intent(view.getContext(), TodaysWeatherActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent =  new Intent(view.getContext(), SensortagActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
