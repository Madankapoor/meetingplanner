package com.swiggy.findatime.scheduler;

import com.swiggy.findatime.models.Meeting;
import com.swiggy.findatime.models.MeetingTime;
import com.swiggy.findatime.models.Resource;

import java.util.*;

public class SchedulerImpl implements Scheduler {
    private Integer minTime = 1;
    private Map<Date, Map<Integer, Set<Resource>>>  schedularMap = new HashMap<>();

    public Map<Integer, Set<Resource> > getOverLappedResources(Set<Resource> resources, MeetingTime time){
        Map<Integer, Set<Resource>> overLapMap = new HashMap<>();
        Map<Integer, Set<Resource>> dateSchedule = getDateSchedule(time.date);
        for(Integer currentTime = time.startTime; currentTime < time.startTime + time.duration; currentTime += minTime)
        if(dateSchedule.containsKey(currentTime))
        {
            Set<Resource> currentResources = dateSchedule.get(currentTime);
            for(Resource resource: resources ){
                if(currentResources.contains(resource)){
                    // Check and Add the current Key
                    if(!overLapMap.containsKey(currentTime))
                        overLapMap.put( currentTime , new HashSet<>());
                    overLapMap.get( currentTime ).add(resource);
                }
            }
        }
        return overLapMap;
    }

    public Boolean scheduleMeeting(Set<Resource> resources, MeetingTime time, String meetingDescription){
        Map<Integer, Set<Resource>> overLapMap = getOverLappedResources(resources,time);
        if(overLapMap.size() > 0 )
            return false;
        Meeting meeting = new Meeting(meetingDescription);
        meeting.addAllResources(resources);
        for(Integer currentTime = time.startTime; currentTime < time.startTime + time.duration; currentTime += minTime) {
            if (!getDateSchedule(time.date).containsKey(currentTime))
                getDateSchedule(time.date).put(currentTime, new HashSet<>());
            getDateSchedule(time.date).get(currentTime).addAll(resources);
        }
        return true;
    }

    @Override
    public List<MeetingTime> scheduleSuggestions(Set<Resource> resources, MeetingTime time, String meetingDescription) {
        List<MeetingTime> suggestedTimes = new ArrayList<>();
        for(Integer currentTime = time.startTime; currentTime < time.startTime + time.duration; currentTime += minTime)
        {
            Set<Resource> currentResources = getCurrentTimeSchedule(time.date,currentTime);
            Boolean noOverlap = true;
            for(Resource resource: resources ){
                if(currentResources.contains(resource)){
                    // Check and Add the current Key
                    noOverlap = false;
                    break;
                }
            }

            if(noOverlap) {
                suggestedTimes.add(new MeetingTime(time.date, currentTime, time.duration));
                return suggestedTimes;
            }
        }
        return suggestedTimes;
    }

    private Map<Integer, Set<Resource>> getDateSchedule(Date date){
        if(!schedularMap.containsKey(date))
            schedularMap.put(date,new HashMap<>());
        return schedularMap.get(date);
    }
    private Set<Resource> getCurrentTimeSchedule(Date date, Integer currentTime){
        if(!getDateSchedule(date).containsKey(currentTime))
            getDateSchedule(date).put(currentTime, new HashSet<>());
        return getDateSchedule(date).get(currentTime);
    }
}
