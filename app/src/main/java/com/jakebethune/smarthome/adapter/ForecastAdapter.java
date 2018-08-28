package com.jakebethune.smarthome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakebethune.smarthome.R;
import com.jakebethune.smarthome.model.Forecast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bethunej01 on 8/10/17.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    private static final String TAG = "TAG";
    private Context context;
    private ArrayList<Forecast> forecast;
    private ImageView icon;


    public ForecastAdapter(Context context, ArrayList<Forecast> forecast) {
        this.forecast = forecast;
        this.context = context;
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int day = forecast.get(position).getDay();
        if (day == 1) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String newDate = dateFormat.format(date);
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String newDay = simpleDateformat.format(date);
            holder.dayText.setText(newDay + " - " + newDate);
        }

        if (day == 2) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String newDate = dateFormat.format(date);
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(dateFormat.parse(newDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.add(Calendar.DATE, 1);
            String convertedDate=dateFormat.format(cal.getTime());
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String newDay = simpleDateformat.format(date);
            newDay = getDayOfWeek(newDay);
            holder.dayText.setText(newDay + " - " + convertedDate);
        }

        if (day == 3) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String newDate = dateFormat.format(date);
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(dateFormat.parse(newDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.add(Calendar.DATE, 2);
            String convertedDate=dateFormat.format(cal.getTime());
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String newDay = simpleDateformat.format(date);
            newDay = getDayOfWeek(newDay);
            newDay = getDayOfWeek(newDay);
            holder.dayText.setText(newDay + " - " + convertedDate);
        }

        if (day == 4) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String newDate = dateFormat.format(date);
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(dateFormat.parse(newDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.add(Calendar.DATE, 3);
            String convertedDate=dateFormat.format(cal.getTime());
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String newDay = simpleDateformat.format(date);
            newDay = getDayOfWeek(newDay);
            newDay = getDayOfWeek(newDay);
            newDay = getDayOfWeek(newDay);
            holder.dayText.setText(newDay + " - " + convertedDate);
        }

        if (day == 5) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String newDate = dateFormat.format(date);
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(dateFormat.parse(newDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.add(Calendar.DATE, 4);
            String convertedDate=dateFormat.format(cal.getTime());
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
            String newDay = simpleDateformat.format(date);
            newDay = getDayOfWeek(newDay);
            newDay = getDayOfWeek(newDay);
            newDay = getDayOfWeek(newDay);
            newDay = getDayOfWeek(newDay);
            holder.dayText.setText(newDay + " - " + convertedDate);
        }

        String clouds = forecast.get(position).getClouds() + "%";
        holder.cloudsText.setText(clouds);
        String humidity = forecast.get(position).getHumidity() + "%";
        holder.humidityText.setText(humidity);
        String description = forecast.get(position).getDescription();
        holder.descriptionText.setText(description);
        String dayTemp = forecast.get(position).getDayTemp() + "°C";
        holder.dayTempText.setText(dayTemp);
        String maxTemp = forecast.get(position).getMaxTemp() + "°C";
        holder.maxTempText.setText(maxTemp);
        String minTemp = forecast.get(position).getMinTemp() + "°C";
        holder.minTempText.setText(minTemp);
        String weather = forecast.get(position).getWeather();
        holder.weatherText.setText(weather);
        String windDirection = forecast.get(position).getWindDirection() + "°";
        holder.windDirectionText.setText(windDirection);
        String windSpeed = forecast.get(position).getWindSpeed() + "m/s";
        holder.windSpeedText.setText(windSpeed);
        String eveTemp = forecast.get(position).getEveTemp() + "°C";
        holder.eveningTempText.setText(eveTemp);
        String nightTemp = forecast.get(position).getNightTemp() + "°C";
        holder.nightTempText.setText(nightTemp);
        String pressure = forecast.get(position).getPressure() + " hPa";
        holder.pressureText.setText(pressure);
        String morningTemp = forecast.get(position).getMorningTemp() + "°C";
        holder.morningTempText.setText(morningTemp);

        if(weather.equals("Clear")) {
            holder.icon.setImageResource(R.mipmap.clear_sky);
        }

        if(weather.equals("Clouds")) {
            if(description.equals("few clouds")) {
                holder.icon.setImageResource(R.mipmap.few_clouds);
            }
            if(description.equals("scattered clouds")) {
                holder.icon.setImageResource(R.mipmap.scattered_clouds);
            }
            else {
                holder.icon.setImageResource(R.mipmap.broken_clouds);
            }
        }

        if(weather.equals("Rain"))
        {
            if (description.equals("light rain") || (description.equals("moderate rain") || (description.equals("heavy intensity rain") || (description.equals("very heavy rain") || (description.equals("extreme rain")))))) {
                holder.icon.setImageResource(R.mipmap.rain);
            }
            else if (description.equals("freezing rain")) {
                holder.icon.setImageResource(R.mipmap.snow);
            }
            else {
                holder.icon.setImageResource(R.mipmap.shower_rain);
            }
        }

        if(weather.equals("Thunderstorm")) {
            holder.icon.setImageResource(R.mipmap.thunderstorm);
        }

        if(weather.equals("Drizzle")) {
            holder.icon.setImageResource(R.mipmap.shower_rain);
        }

        if(weather.equals("Snow")) {
            holder.icon.setImageResource(R.mipmap.snow);
        }

        if(weather.equals("Atmosphere")) {
            holder.icon.setImageResource(R.mipmap.mist);
        }

        if(weather.equals("Extreme")) {
            holder.icon.setImageResource(R.mipmap.extreme);
        }

        if(weather.equals("Additional")) {
            if((description.equals("calm") || (description.equals("light breeze") || (description.equals("gentle breeze") || (description.equals("moderate breeze") || (description.equals("fresh breeze") || (description.equals("strong breeze")))))))) {
                holder.icon.setImageResource(R.mipmap.wind);
            }
            else {
                holder.icon.setImageResource(R.mipmap.extreme);
            }
        }

    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayText;
        TextView cloudsText;
        TextView descriptionText;
        TextView humidityText;
        TextView dayTempText;
        TextView maxTempText;
        TextView minTempText;
        TextView weatherText;
        TextView windDirectionText;
        TextView windSpeedText;
        TextView nightTempText;
        TextView pressureText;
        TextView morningTempText;
        TextView eveningTempText;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            dayText = (TextView) itemView.findViewById(R.id.dayHeading);
            cloudsText = (TextView) itemView.findViewById(R.id.cloudValue);
            descriptionText = (TextView) itemView.findViewById(R.id.detailValue);
            humidityText = (TextView) itemView.findViewById(R.id.humidityValue);
            dayTempText = (TextView) itemView.findViewById(R.id.dayTempValue);
            maxTempText = (TextView) itemView.findViewById(R.id.maxTempValue);
            minTempText = (TextView) itemView.findViewById(R.id.minTempValue);
            weatherText = (TextView) itemView.findViewById(R.id.weatherValue);
            windDirectionText = (TextView) itemView.findViewById(R.id.windDirectionValue);
            windSpeedText = (TextView) itemView.findViewById(R.id.windSpeedValue);
            pressureText = (TextView) itemView.findViewById(R.id.pressureValue);
            nightTempText = (TextView) itemView.findViewById(R.id.nightTempValue);
            morningTempText = (TextView) itemView.findViewById(R.id.morningTempValue);
            eveningTempText = (TextView) itemView.findViewById(R.id.eveningTempValue);
            icon = (ImageView) itemView.findViewById(R.id.weatherIcon);
        }

    }

    public String getDayOfWeek(String newDay) {
        if(newDay.equals("Monday")){
            return newDay = "Tuesday";
        }
        if(newDay.equals("Tuesday")){
            return newDay = "Wednesday";
        }
        if(newDay.equals("Wednesday")){
            return newDay = "Thursday";
        }
        if(newDay.equals("Thursday")){
            return newDay = "Friday";
        }
        if(newDay.equals("Friday")){
            return newDay = "Saturday";
        }
        if(newDay.equals("Saturday")){
            return newDay = "Sunday";
        }
        if(newDay.equals("Sunday")){
            return newDay = "Monday";
        }
        else {
            return null;
        }
    }
}
