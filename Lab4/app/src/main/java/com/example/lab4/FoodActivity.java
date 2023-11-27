package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnOrderFood = findViewById(R.id.btnOrderFood);
        List<String> foodList = new ArrayList<>();
        foodList.add("Phở Hà Nội");
        foodList.add("Bún Bò Huế");
        foodList.add("Mì Quảng");
        foodList.add("Hủ Tíu Sài Gòn");
        String orderedFood = getIntent().getStringExtra("selected");
        foodList.forEach(food -> {
            RadioButton radioButton = new RadioButton(FoodActivity.this);
            radioButton.setText(food);
            radioButton.setPadding(12, 0, 0, 0);
            radioButton.setTextSize(18);
            radioButton.setTextColor(Color.parseColor("#4A4A4A"));
            radioButton.setTextAlignment(RadioButton.TEXT_ALIGNMENT_CENTER);
            radioGroup.addView(radioButton);
            if(orderedFood != null && orderedFood.equalsIgnoreCase(food)){
                radioGroup.check(radioButton.getId());
            }
        });
        btnOrderFood.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(FoodActivity.this, "Bạn chưa chọn món ăn nào.", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton radioButton = findViewById(selectedId);
            String food = radioButton.getText().toString();
            Intent intent = new Intent(FoodActivity.this, Question1MainActivity.class);
            intent.putExtra("orderedFood", food);
            startActivity(intent);
        });
    }
}