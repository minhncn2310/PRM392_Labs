package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Question1MainActivity extends AppCompatActivity {
    private static String orderedFood = null;
    private static String orderedDrink = null;
    private String notOrderFoodYet = "Chưa đặt món ăn";
    private String notOrderDrinkYet = "Chưa đặt đồ uống";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1_main);

        Button btnOrderFood = findViewById(R.id.btnOrderFood);
        Button btnOrderDrink = findViewById(R.id.btnOrderDrink);
        Button btnQuit = findViewById(R.id.btnQuit);
        TextView tvOrderedFood = findViewById(R.id.tvOrderedFood);

        String newOrderedFood = getIntent().getStringExtra("orderedFood");
        String newOrderedDrink = getIntent().getStringExtra("orderedDrink");
        String orderFoodAndDrink = "";
        if (newOrderedFood != null) {
            orderedFood = newOrderedFood;
        }
        if (orderedFood != null) {
            orderFoodAndDrink = orderedFood + " - ";
        } else {
            orderFoodAndDrink = notOrderFoodYet + " - ";
        }

        if (newOrderedDrink != null) {
            orderedDrink = newOrderedDrink;
        }
        if (orderedDrink != null) {
            orderFoodAndDrink += orderedDrink;
        } else {
            orderFoodAndDrink += notOrderDrinkYet;
        }
        tvOrderedFood.setText(orderFoodAndDrink);
        btnOrderFood.setOnClickListener(v -> {
            Intent intent = new Intent(Question1MainActivity.this, FoodActivity.class);
            intent.putExtra("selected",orderedFood);
            startActivity(intent);
        });

        btnOrderDrink.setOnClickListener(v -> {
            Intent intent = new Intent(Question1MainActivity.this, DrinkActivity.class);
            intent.putExtra("selected",orderedDrink);
            startActivity(intent);
        });

        btnQuit.setOnClickListener(v -> {
            new AlertDialog.Builder(Question1MainActivity.this)
                    .setTitle("Thoát")
                    .setMessage("Bạn có chắc chắn muốn thoát ứng dụng?")
                    .setPositiveButton("Thoát", (d, w) -> {
                        Intent intent = new Intent(Question1MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("Huỷ", null)
                    .show();
        });
    }
}