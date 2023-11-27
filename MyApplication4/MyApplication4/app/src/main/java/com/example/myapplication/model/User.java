package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import kotlin.jvm.internal.SerializedIr;

public class User {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
