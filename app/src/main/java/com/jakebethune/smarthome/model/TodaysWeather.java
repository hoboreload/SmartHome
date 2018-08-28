package com.jakebethune.smarthome.model;

/**
 * Created by bethunej01 on 4/10/17.
 */

public class TodaysWeather {
    private String timestamp;
    private int clouds;
    private double currentTemp;
    private String detail;
    private int humidity;
    private double maxTemp;
    private double minTemp;
    private String sunrise;
    private String sunset;
    private String weather;
    private int windDirection;
    private double windSpeed;

    public TodaysWeather() {

    }

    public TodaysWeather(String timestamp, int clouds, double currentTemp, String detail, int humidity, double maxTemp, double minTemp, String sunrise, String sunset, String weather, int windDirection, double windSpeed) {
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

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
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

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
