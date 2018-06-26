package com.jakebethune.smarthome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bethunej01 on 26/2/18.
 */

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private final String[] gridviewText;
    private final int[] gridViewImage;

    public HomeAdapter(Context context, String[] gridviewText, int[] gridViewImage) {
        this.context = context;
        this.gridViewImage = gridViewImage;
        this.gridviewText = gridviewText;

    }

    @Override
    public int getCount() {
        return gridviewText.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.layout_home, null);
            TextView textViewAndroid = (TextView) gridView.findViewById(R.id.gridview_text);
            ImageView imageViewAndroid = (ImageView) gridView.findViewById(R.id.gridview_image);
            textViewAndroid.setText(gridviewText[position]);
            imageViewAndroid.setImageResource(gridViewImage[position]);
        }
        else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
