package com.jakebethune.smarthome.model;

/**
 * Created by bethunej01 on 4/10/17.
 */

public class TodaysWeather {
    private String timestamp;
    private String clouds;
    private String currentTemp;
    private String detail;
    private String humidity;
    private String maxTemp;
    private String minTemp;
    private String sunrise;
    private String sunset;
    private String weather;
    private String windDirection;
    private String windSpeed;

    public TodaysWeather() {

    }

    public TodaysWeather(String timestamp, String clouds, String currentTemp, String detail, String humidity, String maxTemp, String minTemp, String sunrise, String sunset, String weather, String windDirection, String windSpeed) {
        this.timestamp = timestamp;
        this.clouds = clouds;
        this.currentTemp = currentTemp;
        this.detail = detail;
        this.humidity = humidity;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.weather = weather;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return timestamp.substring(timestamp.length() - 5, timestamp.length());
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}
