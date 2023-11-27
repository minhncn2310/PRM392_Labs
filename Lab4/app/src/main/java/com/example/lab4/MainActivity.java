package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnQuestion1 = (Button) findViewById(R.id.question1);
        Button btnQuestion2 = (Button) findViewById(R.id.question2);
        Button btnQuestion3 = (Button) findViewById(R.id.question3);
        btnQuestion1.setOnClickListener(this);
        btnQuestion2.setOnClickListener(this);
        btnQuestion3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Class<?> questionTo = MainActivity.class;
        if(viewId == R.id.question1) questionTo = Question1MainActivity.class;
        if(viewId == R.id.question2) questionTo = Question2MainActivity.class;
        if(viewId == R.id.question3) questionTo = Question3MainActivity.class;
        Intent intent = new Intent(this, questionTo);
        finish();
        startActivity(intent);
    }
}