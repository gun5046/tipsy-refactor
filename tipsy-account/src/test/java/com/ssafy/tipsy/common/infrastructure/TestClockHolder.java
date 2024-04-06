package com.ssafy.tipsy.common.infrastructure;

import com.ssafy.tipsy.common.util.ClockHolder;

import java.util.Date;

public class TestClockHolder implements ClockHolder {
    public TestClockHolder(long time) {
        this.time = time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    long time;

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public Date getDate() {
        return new Date(time);
    }
}
