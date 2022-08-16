package com.example.mapmopad;

import io.realm.RealmObject;

public class Keyword extends RealmObject {

    public String category;

    public int num;

    public int getNum() { return num; }

    public void setNum(int num) { this.num = num; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

}
