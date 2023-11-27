package com.example.prm392_lab5_recycleview_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<User> userArrayList;
    RecyclerView rvUser;
    FloatingActionButton btnAdd;
    UserAdapter userAdapter;
    Button btnSave;
    EditText etUsername, etFullname, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Ánh xạ
        rvUser = findViewById(R.id.RecycleView1);
        btnAdd = findViewById(R.id.btnAdd);

        // mở màn hình khi bấm btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(UserActivity.this);
                dialog.setContentView(R.layout.add_user);

                etUsername = dialog.findViewById(R.id.etUsername);
                etFullname = dialog.findViewById(R.id.etFullname);
                etEmail = dialog.findViewById(R.id.etEmail);
                btnSave = dialog.findViewById(R.id.btnSave);

                // khi kích hoạt nút Save
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username="", fullname="", email="";
                        if(!etUsername.getText().toString().equals("")){
                            username = etUsername.getText().toString();
                        }else {
                            Toast.makeText(UserActivity.this, "Please enter Username", Toast.LENGTH_SHORT).show();
                        }
                        if(!etFullname.getText().toString().equals("")){
                            fullname = etFullname.getText().toString();
                        }else {
                            Toast.makeText(UserActivity.this, "Please enter Fullname", Toast.LENGTH_SHORT).show();
                        }
                        if(!etEmail.getText().toString().equals("")){
                            email = etEmail.getText().toString();
                        }else {
                            Toast.makeText(UserActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                        }

                        userArrayList.add(new User(username, fullname, email));
                        userAdapter.notifyItemInserted(userArrayList.size()-1);
                        rvUser.scrollToPosition(userArrayList.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        userArrayList = new ArrayList<>();
        userArrayList.add(new User("letuankiet", "Le Tuan Kiet", "kiet@gmail.com"));
        userArrayList.add(new User("letuankiet1", "Le Tuan Kiet", "kiet@gmail.com"));
        userArrayList.add(new User("letuankiet2", "Le Tuan Kiet", "kiet@gmail.com"));
        userArrayList.add(new User("letuankiet3", "Le Tuan Kiet", "kiet@gmail.com"));
        userArrayList.add(new User("letuankiet4", "Le Tuan Kiet", "kiet@gmail.com"));
        userArrayList.add(new User("letuankiet5", "Le Tuan Kiet", "kiet@gmail.com"));
        userArrayList.add(new User("letuankiet6", "Le Tuan Kiet", "kiet@gmail.com"));
        userArrayList.add(new User("letuankiet7", "Le Tuan Kiet", "kiet@gmail.com"));

        rvUser.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(this,userArrayList);
        rvUser.setAdapter(userAdapter);

    }
}