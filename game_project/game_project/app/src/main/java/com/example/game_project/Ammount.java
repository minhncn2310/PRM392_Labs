package com.example.game_project;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Ammount {
    private String hint;
    private int ammount;

    public Ammount(String hint, int ammount) {
        this.hint = hint;
        this.ammount = ammount;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public String getHint() {
        return hint;
    }
    public int getAmmount() {
        return ammount;
    }

}
