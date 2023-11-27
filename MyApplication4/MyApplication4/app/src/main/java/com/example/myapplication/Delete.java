package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delete extends AppCompatActivity {
    EditText etname, ettitle;

    Button deleteBTN;

    User userDelete = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        etname = findViewById(R.id.name);
        ettitle = findViewById(R.id.email);

        etname.setText(MainActivity.bundle_user.getId() + "");
        ettitle.setText(MainActivity.bundle_user.getTitle().toString());

        deleteBTN = (Button) findViewById(R.id.deleteBTN);

        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserWithId();
                MainActivity.bundle_user = null;
                Intent intent = new Intent(Delete.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void deleteUserWithId() {
        Integer id = Integer.parseInt(etname.getText().toString());
        String name = ettitle.getText().toString();
        Call<User> call = ApiService.apiService.deleteUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    Toast.makeText(Delete.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Delete.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}