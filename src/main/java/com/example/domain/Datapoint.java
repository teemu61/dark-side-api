package com.example.domain;

public class Datapoint {

    private long time;
    private Double tempHi;
    private Double tempLo;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Double getTempHi() {
        return tempHi;
    }

    public void setTempHi(Double tempHi) {
        this.tempHi = tempHi;
    }

    public Double getTempLo() {
        return tempLo;
    }

    public void setTempLo(Double tempLo) {
        this.tempLo = tempLo;
    }
}
