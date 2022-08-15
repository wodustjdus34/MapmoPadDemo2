package com.example.mapmopad;

import io.realm.RealmObject;

public class Note extends RealmObject {
    String description;
    long createdTime;

    public String getDescription() {
        return description;
    }
    public long getCreatedTime() { return createdTime; }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setCreatedTime(long createdTime) { this.createdTime = createdTime; }
}