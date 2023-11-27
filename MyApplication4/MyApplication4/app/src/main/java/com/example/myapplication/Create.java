package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Create extends AppCompatActivity {

    EditText etname, etemail;
    Button btnSave;
    ArrayList<User> arrayUser;

    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etname = findViewById(R.id.name);
        etemail = findViewById(R.id.email);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                Intent intent = new Intent(Create.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createUser() {
        Integer id = Integer.valueOf(etname.getText().toString());
        String title = etemail.getText().toString();
        User user = new User(id, title);
        try {
            Call<User> call = ApiService.apiService.createNew(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null) {
                        Toast.makeText(Create.this, "Save Successfully", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(Create.this, "Save Fail", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }
}