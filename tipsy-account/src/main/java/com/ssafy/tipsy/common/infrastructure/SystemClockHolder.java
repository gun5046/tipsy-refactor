package com.ssafy.tipsy.common.infrastructure;

import com.ssafy.tipsy.common.util.ClockHolder;

import java.time.Clock;
import java.util.Date;

public class SystemClockHolder implements ClockHolder {

    Date date = new Date();
    public long getTime(){
        return date.getTime();
    }
    public Date getDate(){
        return date;
    }
}
