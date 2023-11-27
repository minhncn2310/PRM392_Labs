package com.example.listarray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnLab3_1;
        Button btnLab3_2;

        btnLab3_1 = findViewById(R.id.btnLab3_1);
        btnLab3_2 = findViewById(R.id.btnLab3_2);

        btnLab3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Lab31Activity.class);
                startActivity(intent);
            }
        });

        btnLab3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Lab32Activity.class);
                startActivity(intent);
            }
        });
    }
}