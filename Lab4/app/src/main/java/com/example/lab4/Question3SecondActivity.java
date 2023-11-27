package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Question3SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3_second);
        TextView tvDisplay = findViewById(R.id.tvDisplay);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        if (bundle != null) {
            String str = bundle.getString("string");
            int number = bundle.getInt("number");
            String[] motorBrands = bundle.getStringArray("array");
            StringBuilder arrString = new StringBuilder();
            for (int i = 0; i < motorBrands.length; i++) {
                if (i == motorBrands.length - 1)
                    arrString.append(motorBrands[i]);
                else arrString.append(motorBrands[i]).append(", ");
            }
            Student student = (Student) bundle.getSerializable("student");
            String result = "String: " + str + "\n" + "Number: " + number + "\n" + "Array: " + arrString + "\n" + "Student: " + student.getName() + " - " + student.getId();

            tvDisplay.setText(result);
        }
    }
}