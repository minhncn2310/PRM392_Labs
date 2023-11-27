package com.example.prm392_lab5_recycleview_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ModuleActivity extends AppCompatActivity {
    ArrayList<Module> moduleArrayList;
    RecyclerView rvModule;
    FloatingActionButton btnAdd;
    ModuleAdapter adapter;
    Button btnSave;
    EditText etName, etDescription, etDevice;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        rvModule = findViewById(R.id.recycleView1);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ModuleActivity.this);
                dialog.setContentView(R.layout.add_update);

                etName= dialog.findViewById(R.id.etName);
                etDescription = dialog.findViewById(R.id.etDescription);
                etDevice = dialog.findViewById(R.id.etDevice);
                imgView = dialog.findViewById(R.id.imgAddView);
                btnSave = dialog.findViewById(R.id.btnSave);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            String name="", description="", device="";
                            if(!etName.getText().toString().equals("")){
                                name = etName.getText().toString();
                            }else {
                                Toast.makeText(ModuleActivity.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                            }
                            if(!etDescription.getText().toString().equals("")){
                                description = etDescription.getText().toString();
                            }else {
                                Toast.makeText(ModuleActivity.this, "Please enter description", Toast.LENGTH_SHORT).show();
                            }
                            if(!etDevice.getText().toString().equals("")){
                                device = etDevice.getText().toString();
                            }else {
                                Toast.makeText(ModuleActivity.this, "Please enter device", Toast.LENGTH_SHORT).show();
                            }

                            moduleArrayList.add(new Module(name, description, device, Uri.parse("android.resource://com.example.prm392_lab5_recycleview_v2/" + R.drawable.iconsbird)));
                            adapter.notifyItemInserted(moduleArrayList.size()-1);
                            rvModule.scrollToPosition(moduleArrayList.size()-1);

                        }catch (Exception e){
                            Toast.makeText(ModuleActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        moduleArrayList = new ArrayList<>();
        moduleArrayList.add(new Module("Bird", "Le Tuan Kiet", "kiet@gmail.com", Uri.parse("android.resource://com.example.prm392_lab5_recycleview_v2/" + R.drawable.iconsbird)));
        moduleArrayList.add(new Module("Exam", "Le Tuan Kiet", "kiet@gmail.com", Uri.parse("android.resource://com.example.prm392_lab5_recycleview_v2/" + R.drawable.iconstask)));
        moduleArrayList.add(new Module("Settings", "Le Tuan Kiet", "kiet@gmail.com", Uri.parse("android.resource://com.example.prm392_lab5_recycleview_v2/" + R.drawable.iconssettings)));
        moduleArrayList.add(new Module("Task", "Le Tuan Kiet", "kiet@gmail.com", Uri.parse("android.resource://com.example.prm392_lab5_recycleview_v2/" + R.drawable.iconsexam)));

        rvModule.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ModuleAdapter(this, moduleArrayList);
        rvModule.setAdapter(adapter);
    }
}