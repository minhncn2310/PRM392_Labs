package com.example.listarray;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;
import java.util.ArrayList;

public class Lab31Activity extends AppCompatActivity {
    ListView lvMonhoc;
    EditText etSubjectName;
    int position = -1;
    Button add;
    Button update;
    Button delete;
    Button back;
    ArrayList<String> arrayCourse;
    ArrayAdapter<String> adapter; // Declare ArrayAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab31);

        lvMonhoc = findViewById(R.id.ListViewMonHoc);
        etSubjectName = findViewById(R.id.etMonHoc);
        add = findViewById(R.id.btnAdd);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        back = findViewById(R.id.btnBack);
        arrayCourse = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Initialize ArrayAdapter
        adapter = new ArrayAdapter<>(
                Lab31Activity.this,
                android.R.layout.simple_list_item_1,
                arrayCourse
        );

        lvMonhoc.setAdapter(adapter);

        // Set up the click and long click listeners
        lvMonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // get data from array and show it
                Toast.makeText(Lab31Activity.this, arrayCourse.get(i), Toast.LENGTH_SHORT).show();
                position = i;
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectChanged = etSubjectName.getText().toString();
                arrayCourse.remove(subjectChanged);
                adapter.notifyDataSetChanged();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectChanged = etSubjectName.getText().toString();
                arrayCourse.add(subjectChanged);
                adapter.notifyDataSetChanged();
            }
        });

        // Set up the update button click listener
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectChanged = etSubjectName.getText().toString();
                int selectedPosition = position;
                if (selectedPosition >= 0 && selectedPosition < arrayCourse.size()) {
                    arrayCourse.set(selectedPosition, subjectChanged);
                    adapter.notifyDataSetChanged();
                    position = -1;
                }
            }
        });
    }
}
