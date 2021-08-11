package com.swiggy.findatime.scheduler;

import com.swiggy.findatime.models.MeetingTime;
import com.swiggy.findatime.models.Resource;

import java.util.List;
import java.util.Set;

public interface Scheduler {
    public Boolean scheduleMeeting(Set<Resource> resources, MeetingTime time, String meetingDescription);
    public List<MeetingTime> scheduleSuggestions(Set<Resource> resources, MeetingTime time, String meetingDescription);
}
