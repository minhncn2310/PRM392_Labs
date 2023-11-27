    package com.example.listarray;
    import androidx.appcompat.app.AppCompatActivity;

    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ListView;

    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.util.ArrayList;

    public class Lab32Activity extends AppCompatActivity {
        ListView lvFruit;
        ArrayList<Fruit> arrayFruit;
        FruitAdapter fruitAdapter;
        Button back;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fruit_layout);
            Reference();
            fruitAdapter = new FruitAdapter(this, R.layout.fruit_adapter, arrayFruit);
            lvFruit.setAdapter(fruitAdapter);

            back = findViewById(R.id.btnBack);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
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