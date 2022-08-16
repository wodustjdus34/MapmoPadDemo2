package com.example.mapmopad;

import java.util.Vector;

import io.realm.RealmObject;

public class Note extends RealmObject {
    String description;
    long createdTime;
    Vector<String> wordList;


    public String getDescription() { return description; }
    public long getCreatedTime() { return createdTime; }
    public Vector<String> getWordList() { return wordList; }

    public void setDescription(String description) { this.description = description; }
    public void setCreatedTime(long createdTime) { this.createdTime = createdTime; }
    public void setWordList(Vector<String> wordList) { this.wordList = wordList; }

}