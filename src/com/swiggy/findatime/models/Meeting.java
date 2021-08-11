package com.swiggy.findatime.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Meeting {
    UUID ID;
    String description;
    Set<Resource> attendees;
    public Meeting(String description){
        this.description = description;
        attendees = new HashSet<>();
        ID = UUID.randomUUID();
    }

    public void addAllResources(Set<Resource> resources){
        attendees.addAll(resources);
        for(Resource resource: resources){
            resource.setMeeting(this.ID);
        }
    }
}
