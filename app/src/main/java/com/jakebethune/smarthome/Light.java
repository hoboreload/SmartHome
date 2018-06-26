package com.jakebethune.smarthome;

public class Light {

    private String lightName;
    private int powerState;

    public Light() {
        //Left empty for call by Firebase
    }

    public Light(String lightName, int powerState) {
        this.lightName= lightName;
        this.powerState = powerState;
    }

    public String getLightName() {
        return lightName;
    }

    public void setLightName(String lightName) {
        this.lightName = lightName;
    }

    public int getPowerState() {
        return powerState;
    }

    public void setPowerState(int powerState) {
        this.powerState = powerState;
    }
}
