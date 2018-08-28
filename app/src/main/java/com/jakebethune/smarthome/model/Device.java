package com.jakebethune.smarthome.model;

public class Device {

    private String deviceName;
    private String powerState;

    public Device() {
        //Left empty for call by Firebase
    }

    public Device(String deviceName, String powerState) {
        this.deviceName = deviceName;
        this.powerState = powerState;
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

}
