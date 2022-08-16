package com.example.mapmopad;

import io.realm.RealmObject;

public class Keyword extends RealmObject {
    String object;
    int frequency;

    public Keyword(String object, int frequency) {
        this.object = object;
        this.frequency = frequency;
    }

    public String getObject() {return object;}
    public void setObject(String object) {this.object = object;}

    public int getFrequency() {return frequency;}
    public void setFrequency(int frequency) {this.frequency = frequency;}
}
