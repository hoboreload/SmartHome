package com.jakebethune.smarthome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LightingAdapter extends RecyclerView.Adapter<LightingAdapter.ViewHolder> {

    private static final String TAG = "TAG";
    private Context context;
    private ArrayList<Light> lights;
    private static final String LIGHT = "LIGHT";

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
    }

    @Override
    public int getItemCount() {
        return lights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lightNameText;

        public ViewHolder(View itemView) {
            super(itemView);

            lightNameText = (TextView) itemView.findViewById(R.id.lightName);
            RelativeLayout lightNameLayout = (RelativeLayout) itemView.findViewById(R.id.lightingItemRelativeLayout);
            lightNameLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
