package com.swiggy.findatime.models;

public class Attende extends Resource {
    String email;
    public Attende(String email) {
        super(email);
        this.email = email;
    }
}
