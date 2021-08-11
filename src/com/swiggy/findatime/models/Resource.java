package com.swiggy.findatime.models;

import java.util.Objects;
import java.util.UUID;

public class Resource {
    String ID;
    UUID meeting;
    public Resource(String ID){
        this.ID = ID;
        meeting = null;
    }
    public Resource(String ID, UUID meeting) {
        this.ID = ID;
        this.meeting = meeting;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public UUID getMeeting() {
        return meeting;
    }

    public void setMeeting(UUID meeting) {
        this.meeting = meeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return ID.equals(resource.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
