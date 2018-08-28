package com.jakebethune.smarthome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.Sensortag;

import java.util.ArrayList;

/**
 * Created by bethunej01 on 9/10/17.
 */

public class SensortagAdapter extends RecyclerView.Adapter<SensortagAdapter.ViewHolder> {
    private static final String TAG = "TAG";
    private Context context;
    private ArrayList<Sensortag> sensortag;


    public SensortagAdapter(Context context, ArrayList<Sensortag> sensortag) {
        this.sensortag = sensortag;
        this.context = context;
    }

    @Override
    public SensortagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sensortag, parent, false);
        return new SensortagAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SensortagAdapter.ViewHolder holder, int position) {

        String timestamp = sensortag.get(position).getTimestamp();
        holder.timestampText.setText(timestamp);

        String humidity = sensortag.get(position).getHumidity() + "%";
        holder.humidityText.setText(humidity);

        String ambientTemp = sensortag.get(position).getAmbientTemp() + "°C";
        holder.ambientTempText.setText(ambientTemp);

        String objectTemp = sensortag.get(position).getObjectTemp() + "°C";
        holder.objectTempText.setText(objectTemp);

        String pressure = sensortag.get(position).getPressure() + " hPa";
        holder.pressureText.setText(pressure);

        String light = sensortag.get(position).getLight() + " lux";
        holder.lightText.setText(light);
    }

    @Override
    public int getItemCount() {
        return sensortag.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView timestampText;
        TextView objectTempText;
        TextView ambientTempText;
        TextView lightText;
        TextView humidityText;
        TextView pressureText;

        public ViewHolder(View itemView) {
            super(itemView);

            timestampText = (TextView) itemView.findViewById(R.id.timeHeading);
            lightText = (TextView) itemView.findViewById(R.id.lightValue);
            objectTempText = (TextView) itemView.findViewById(R.id.objectTempValue);
            ambientTempText = (TextView) itemView.findViewById(R.id.ambientTempValue);
            humidityText = (TextView) itemView.findViewById(R.id.humidityValue);
            pressureText = (TextView) itemView.findViewById(R.id.pressureValue);
        }

    }
}

