package com.example.lab3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFruit;
    ArrayList<Fruit> arrayFruit;
    FruitAdapter fruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reference();
        fruitAdapter = new FruitAdapter(this, R.layout.fruit_layout, arrayFruit);
        lvFruit.setAdapter(fruitAdapter);
    }

    private void Reference(){
        lvFruit = (ListView) findViewById(R.id.listViewFruit);
        arrayFruit = new ArrayList<>();
        arrayFruit.add(new Fruit("Apple", "Apple...Some description goes here", R.drawable.apple));
        arrayFruit.add(new Fruit("Grape", "Grape...Some description goes here", R.drawable.grape));
        arrayFruit.add(new Fruit("Lemon", "Lemon...Some description goes here", R.drawable.lemon));
        arrayFruit.add(new Fruit("Banana", "Banana...Some description goes here", R.drawable.download));
        arrayFruit.add(new Fruit("Corn", "Corn...Some description goes here", R.drawable.download__1_));
    }
}