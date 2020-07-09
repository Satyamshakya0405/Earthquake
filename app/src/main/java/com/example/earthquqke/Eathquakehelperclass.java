package com.example.earthquqke;

public class Eathquakehelperclass {
    private String mLocation;
    private long time;
    private double mMag;

    public Eathquakehelperclass(double mMag, String mLocation, long time) {
        this.mMag = mMag;
        this.mLocation = mLocation;
        this.time = time;
    }

    public double getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getTime() {
        return time;
    }
}
