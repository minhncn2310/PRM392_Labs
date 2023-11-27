package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Question3MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3_main);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(Question3MainActivity.this, Question3SecondActivity.class);
            String [] motorBrands = {"Honda", "Yamaha", "Suzuki"};
            Student student = new Student("Minh", "Nhat");
            Bundle bundle = new Bundle();
            bundle.putString("string", "Sử dụng Bundle để truyền dữ liệu giữa các Activity");
            bundle.putInt("number", 1706);
            bundle.putStringArray("array", motorBrands);
            bundle.putSerializable("student", student);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        });
    }
}