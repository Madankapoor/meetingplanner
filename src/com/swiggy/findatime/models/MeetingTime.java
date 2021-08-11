package com.swiggy.findatime.models;

import java.util.Date;

public class MeetingTime {
    public Date date;
    public Integer startTime;
    public Integer duration;
    public Integer startHour;
    public Integer startMinute;

    public MeetingTime(Date date, Integer startTime, Integer duration) {
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.startHour = startTime/60;
        this.startMinute = startTime%60;
    }

    public MeetingTime(Date date, Integer startHour, Integer startMinute, Integer duration) {
        this.date = date;
        this.startTime = startHour*60 + startMinute;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MeetingTime{" +
                "date=" + date +
                ", startTime=" + startHour +
                ":" + startMinute +
                ",  duration=" + duration +
                '}';
    }
}
