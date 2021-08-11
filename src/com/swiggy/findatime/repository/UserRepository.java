package com.swiggy.findatime.repository;

import com.swiggy.findatime.models.User;
import java.util.HashMap;

public class UserRepository {
    HashMap<String,User> users = new HashMap<>();

    public void AddUser(String email){
        users.put(email, new User(email));
    }
}
