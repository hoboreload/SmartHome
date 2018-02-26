package com.jakebethune.smarthome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by bethunej01 on 26/2/18.
 */

public class GridviewActivity extends AppCompatActivity {

    GridView gridview;

    String[] gridviewText = {
            "Home", "Lighting", "Weather", "Alarm", "Reminders", "Profile", "Settings",
    } ;

    int[] gridviewImage = {
            R.drawable.icon_home, R.drawable.icon_lighting, R.drawable.icon_weather, R.drawable.icon_alarm, R.drawable.icon_reminders, R.drawable.icon_profile,
            R.drawable.icon_settings,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        GridviewAdapter adapterViewAndroid = new GridviewAdapter(GridviewActivity.this, gridviewText, gridviewImage);
        gridview = (GridView) findViewById(R.id.menu_gridview);
        gridview.setAdapter(adapterViewAndroid);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(GridviewActivity.this, "GridView Item: " + gridviewText[+i], Toast.LENGTH_LONG).show();
            }
        });
    }
}
