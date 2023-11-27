package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {
    private RecyclerView rcvUsers;
    private List<User> mListUser;

    Button btn, btnUpdate, btnDelete;

    TextView txtname, tv_id;

    public static int index = -1;

    public static User bundle_user = null;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvUsers = findViewById(R.id.rcv_users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUsers.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUsers.addItemDecoration(itemDecoration);
        btn = (Button) findViewById(R.id.button);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        mListUser = new ArrayList<>();

        txtname = (TextView) findViewById(R.id.getName);
        tv_id = (TextView) findViewById(R.id.getId);

        callApiGetUsers();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Create.class);
                startActivity(intent);
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index >= 0) {
                    Intent intent = new Intent(MainActivity.this, Update.class);
                    startActivity(intent);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index >= 0) {
                    Intent intent = new Intent(MainActivity.this, Delete.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void callApiGetUsers() {
        ApiService.apiService.getListUsers(1).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mListUser = response.body();
                UserAdapter userAdapter = new UserAdapter(mListUser, MainActivity.this);
                rcvUsers.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        // Handle item click here and use the position
        index = position;
        Log.d("Item Clicked", "Position: " + position);
        User user = mListUser.get(index);
        bundle_user = mListUser.get(index);
        tv_id.setText(user.getId() + "");
        txtname.setText(user.getTitle());

    }

}