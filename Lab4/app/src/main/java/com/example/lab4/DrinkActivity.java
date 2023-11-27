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

public class DrinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnOrderDrink = findViewById(R.id.btnOrderDrink);

        List<String> drinkList = new ArrayList<>();
        drinkList.add("Pepsi");
        drinkList.add("Heineken");
        drinkList.add("Tiger");
        drinkList.add("Sài gòn Đỏ");
        String orderedDrink = getIntent().getStringExtra("selected");
        drinkList.forEach(drink -> {
            RadioButton radioButton = new RadioButton(DrinkActivity.this);
            radioButton.setText(drink);
            radioButton.setPadding(12, 0, 0, 0);
            radioButton.setTextSize(18);
            radioButton.setTextColor(Color.parseColor("#4A4A4A"));
            radioButton.setTextAlignment(RadioButton.TEXT_ALIGNMENT_CENTER);
            radioGroup.addView(radioButton);
            if(orderedDrink != null && orderedDrink.equalsIgnoreCase(drink)){
                radioGroup.check(radioButton.getId());
            }
        });

        btnOrderDrink.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(DrinkActivity.this, "Bạn chưa chọn đồ uống nào.", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton radioButton = findViewById(selectedId);
            String drink = radioButton.getText().toString();
            Intent intent = new Intent(DrinkActivity.this, Question1MainActivity.class);
            intent.putExtra("orderedDrink", drink);
            startActivity(intent);
        });
    }
}