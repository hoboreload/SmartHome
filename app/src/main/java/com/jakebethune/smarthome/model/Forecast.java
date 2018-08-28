package com.jakebethune.smarthome.model;

/**
 * Created by bethunej01 on 8/10/17.
 */

public class Forecast {

    private int clouds;
    private int day;
    private int dayTemp;
    private String description;
    private int eveTemp;
    private int humidity;
    private int maxTemp;
    private int minTemp;
    private int morningTemp;
    private int nightTemp;
    private double pressure;
    private String weather;
    private int windDirection;
    private double windSpeed;

    public Forecast() {

    }

    public Forecast(int clouds, int day, int dayTemp, String description, int eveTemp, int humidity, int maxTemp, int minTemp, int morningTemp, int nightTemp, double pressure, String weather, int windDirection, double windSpeed) {
        this.clouds = clouds;
        this.day = day;
        this.dayTemp = dayTemp;
        this.description = description;
        this.eveTemp = eveTemp;
        this.humidity = humidity;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.morningTemp = morningTemp;
        this.nightTemp = nightTemp;
        this.pressure = pressure;
        this.weather = weather;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(int dayTemp) {
        this.dayTemp = dayTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(int eveTemp) {
        this.eveTemp = eveTemp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMorningTemp() {
        return morningTemp;
    }

    public void setMorningTemp(int morningTemp) {
        this.morningTemp = morningTemp;
    }

    public int getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(int nightTemp) {
        this.nightTemp = nightTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
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
