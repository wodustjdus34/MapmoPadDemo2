package com.example.mapmopad;

import io.realm.RealmObject;

public class User extends RealmObject {
    String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
