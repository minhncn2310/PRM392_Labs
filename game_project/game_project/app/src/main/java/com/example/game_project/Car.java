package com.example.game_project;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Car {
    private SeekBar seekBar;
    private CheckBox checkBox;
    private ImageView badge;
    private String name;

    public Car(SeekBar seekBar, CheckBox checkBox, ImageView badge, String name) {
        this.seekBar = seekBar;
        this.checkBox = checkBox;
        this.badge = badge;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public SeekBar getSeekBar() {
        return seekBar;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public ImageView getBadge() {
        return badge;
    }
}
