package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText minEditText;
    private EditText maxEditText;
    private TextView resultTextReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_layout);

        minEditText = findViewById(R.id.editTextMin);
        maxEditText = findViewById(R.id.editTextMax);
        resultTextReview = findViewById(R.id.textView7);

        Button generateButton = findViewById(R.id.button);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomNumber();
                }
            }
        );
    }

    public void generateRandomNumber(){
        try{
            int min = Integer.parseInt(minEditText.getText().toString());
            int max = Integer.parseInt(maxEditText.getText().toString());
            if(min >= max){
                resultTextReview.setText(min+" cannot bigger than "+max);
                return;
            }
            Random random = new Random();
            int randomNumber = random.nextInt((max-min)+1)+min;
            resultTextReview.setText(""+randomNumber);
        }catch (Exception ex){
            resultTextReview.setText("Cannot Generate");
        }
    }
}