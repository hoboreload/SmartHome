package com.jakebethune.smarthome.model;

/**
 * Created by bethunej01 on 8/10/17.
 */

public class Sensortag {
    private String ambientTemp;
    private String humidity;
    private String light;
    private String objectTemp;
    private String pressure;
    private String timestamp;

    public Sensortag() {

    }

    public Sensortag(String ambientTemp, String humidity, String light, String objectTemp, String pressure, String timestamp) {
        this.ambientTemp = ambientTemp;
        this.humidity = humidity;
        this.light = light;
        this.objectTemp = objectTemp;
        this.pressure = pressure;
        this.timestamp = timestamp;
    }

    public String getAmbientTemp() {
        return ambientTemp;
    }

    public void setAmbientTemp(String ambientTemp) {
        this.ambientTemp = ambientTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getObjectTemp() {
        return objectTemp;
    }

    public void setObjectTemp(String objectTemp) {
        this.objectTemp = objectTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}