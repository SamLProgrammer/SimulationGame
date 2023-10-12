package com.example.engine;

public class TimeFrame {
    
    private long lastStamp;

    public TimeFrame() {
        lastStamp = System.currentTimeMillis();
    }

    public double getDT() {
        long currentStamp = System.currentTimeMillis();
        double dt = currentStamp - lastStamp;
        lastStamp = currentStamp;
        return dt;
    }

}
