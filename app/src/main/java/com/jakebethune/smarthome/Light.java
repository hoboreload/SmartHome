package com.jakebethune.smarthome;

public class Light {

    private String lightName;
    private String powerState;

    public Light() {
        //Left empty for call by Firebase
    }

    public Light(String lightName, String powerState) {
        this.lightName= lightName;
        this.powerState = powerState;
    }

    public String getLightName() {
        return lightName;
    }

    public void setLightName(String lightName) {
        this.lightName = lightName;
    }

    public String getPowerState() {
        return powerState;
    }

    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

}
