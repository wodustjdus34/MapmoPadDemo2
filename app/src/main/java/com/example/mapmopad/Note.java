package com.example.mapmopad;

import io.realm.RealmObject;

public class Note extends RealmObject {
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}