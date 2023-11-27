package com.example.prm392_lab5_recycleview_v2;

import android.net.Uri;

public class Module {
    private String name;
    private String description;
    private String device;
    Uri images;

    public Module(String name, String description, String device, Uri images) {
        this.name = name;
        this.description = description;
        this.device = device;
        this.images = images;
    }

    public Module(String name, String description, String device) {
        this.name = name;
        this.description = description;
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Uri getImages() {
        return images;
    }

    public void setImages(Uri images) {
        this.images = images;
    }
}
