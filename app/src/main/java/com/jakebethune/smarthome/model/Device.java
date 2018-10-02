package com.jakebethune.smarthome.model;

public class Device {

    private String deviceName;
    private String powerState;
    private int onTemp;
    private int offTemp;
    private String onTime;
    private String offTime;
    private boolean tempOverride;
    private boolean timeOverride;

    public Device() {
        //Left empty for call by Firebase
    }

    public Device(String deviceName, String powerState, int onTemp, int offTemp, String onTime, String offTime, boolean tempOverride, boolean timeOverride) {
        this.deviceName = deviceName;
        this.powerState = powerState;
        this.onTemp = onTemp;
        this.offTemp = offTemp;
        this.onTime = onTime;
        this.offTime = offTime;
        this.tempOverride = tempOverride;
        this.timeOverride = timeOverride;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public boolean isTempOverride() {
        return tempOverride;
    }

    public void setTempOverride(boolean tempOverride) {
        this.tempOverride = tempOverride;
    }

    public boolean isTimeOverride() {
        return timeOverride;
    }

    public void setTimeOverride(boolean timeOverride) {
        this.timeOverride = timeOverride;
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

    public int getOnTemp() {
        return onTemp;
    }

    public void setOnTemp(int onTemp) {
        this.onTemp = onTemp;
    }

    public int getOffTemp() {
        return offTemp;
    }

    public void setOffTemp(int offTemp) {
        this.offTemp = offTemp;
    }
}
