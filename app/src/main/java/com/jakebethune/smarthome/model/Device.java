package com.jakebethune.smarthome.model;

public class Device {

    private String deviceName;
    private String powerState;
    private int minTemp;
    private int maxTemp;

    public Device() {
        //Left empty for call by Firebase
    }

    public Device(String deviceName, String powerState, int minTemp, int maxTemp) {
        this.deviceName = deviceName;
        this.powerState = powerState;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPowerState() {
        return powerState;
    }

    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }
}
