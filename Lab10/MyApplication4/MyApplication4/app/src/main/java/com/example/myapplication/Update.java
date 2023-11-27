package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update extends AppCompatActivity {

    EditText etname, ettitle;
    Button btnUpdate;

    User userUpdate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etname = findViewById(R.id.txt_name);
        ettitle = findViewById(R.id.txt_title);
        btnUpdate = findViewById(R.id.btnUpdate);


        etname.setText(MainActivity.bundle_user.getId() + "");
        ettitle.setText(MainActivity.bundle_user.getTitle().toString());
        Log.d("bebebe" + MainActivity.bundle_user.getId(), "bebe2: " + MainActivity.bundle_user.getTitle());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserWithId();
                MainActivity.bundle_user = null;
                Intent intent = new Intent(Update.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

        private void updateUserWithId() {
            Integer id = Integer.parseInt(etname.getText().toString());
            String name = ettitle.getText().toString();
            if(id >= 0 && !name.isEmpty())
            {
                userUpdate = new User(id, name);
                ApiService.apiService.updateUser(id, userUpdate).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            Toast.makeText(Update.this, "Update Successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(Update.this, "onFailure", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }
