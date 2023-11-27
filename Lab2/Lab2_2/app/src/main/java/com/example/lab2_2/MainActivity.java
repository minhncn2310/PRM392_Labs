package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText soThuNhat;
    private EditText soThuHai;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soThuNhat = findViewById(R.id.editText1);
        soThuHai = findViewById(R.id.editText2);
        resultTextView = findViewById(R.id.textView7);

        Button cong = findViewById(R.id.btnCong);
        Button tru = findViewById(R.id.btnTru);
        Button nhan = findViewById(R.id.btnNhan);
        Button chia = findViewById(R.id.btnChia);

        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCal('+');
            }
        });
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCal('-');
            }
        });
        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCal('*');
            }
        });
        chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCal('/');
            }
        });
    }

    public void doCal(char operator) {
        double number1 = Double.parseDouble(soThuNhat.getText().toString());
        double number2 = Double.parseDouble(soThuHai.getText().toString());
        double result = 0.0;

        if (operator == '+') {
            result = number1 + number2;
        } else if (operator == '-') {
            result = number1 - number2;
        } else if (operator == '*') {
            result = number1 * number2;
        } else if (operator == '/') {
            if (number2 == 0) {
                resultTextView.setText("Cannot Divide by 0");
                return;
            } else {
                result = number1 / number2;
            }
        }

        resultTextView.setText(String.valueOf(result));
    }
}
