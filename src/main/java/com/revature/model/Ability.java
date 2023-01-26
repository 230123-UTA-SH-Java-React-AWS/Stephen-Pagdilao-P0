package com.revature.model;

public class Ability {
    //Uniquely identifiable field
    private int abId;

    private int PP;
    private int accuracy;
    private int power;
    
    public int getAbId() {
        return abId;
    }
    public void setAbId(int abId) {
        this.abId = abId;
    }
    public int getPP() {
        return PP;
    }
    public void setPP(int pP) {
        PP = pP;
    }
    public int getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    
}
