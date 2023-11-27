package com.example.prm392_lab5_recycleview_v2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {
    Context context;
    private ArrayList<Module> moduleArrayList;
    EditText etName, etDescription, etDevice;
    ImageView imgView;
    Button btnSave;

    public ModuleAdapter(Context context, ArrayList<Module> moduleArrayList) {
        this.context = context;
        this.moduleArrayList = moduleArrayList;
    }

    @NonNull
    @Override
    public ModuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.row_modulelist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Module module = moduleArrayList.get(position);

        holder.tvName.setText(module.getName());
        holder.tvDesName.setText(module.getDescription());
        holder.tvDevice.setText(module.getDevice());
        holder.imgView.setImageURI(module.getImages());

        holder.linearLayoutAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update);

                // ánh xạ
                etName= dialog.findViewById(R.id.etName);
                etDescription = dialog.findViewById(R.id.etDescription);
                etDevice = dialog.findViewById(R.id.etDevice);
                imgView = dialog.findViewById(R.id.imgAddView);
                btnSave = dialog.findViewById(R.id.btnSave);

                etName.setText((moduleArrayList.get(position)).getName());
                etDescription.setText((moduleArrayList.get(position)).getDescription());
                etDevice.setText((moduleArrayList.get(position)).getDevice());
                imgView.setImageURI(moduleArrayList.get(position).getImages());

                btnSave.setText("Update");
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="", description="", device="";
                        if(!etName.getText().toString().equals("")){
                            name = etName.getText().toString();
                        }else {
                            Toast.makeText(context, "Please enter Name", Toast.LENGTH_SHORT).show();
                        }
                        if(!etDescription.getText().toString().equals("")){
                            description = etDescription.getText().toString();
                        }else {
                            Toast.makeText(context, "Please enter description", Toast.LENGTH_SHORT).show();
                        }
                        if(!etDevice.getText().toString().equals("")){
                            device = etDevice.getText().toString();
                        }else {
                            Toast.makeText(context, "Please enter device", Toast.LENGTH_SHORT).show();
                        }
                        moduleArrayList.set(position, new Module(name, description, device, Uri.parse("android.resource://com.example.prm392_lab5_recycleview_v2/" + R.drawable.iconsbird)));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        // nhấn giữ để xóa
        holder.linearLayoutAll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // hiện thông báo
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Module")
                        .setMessage("Are you sure want to delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moduleArrayList.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvDesName, tvDevice;
        ImageView imgView;
        LinearLayout linearLayoutAll;

        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesName =  itemView.findViewById(R.id.tvDescription);
            tvDevice = itemView.findViewById(R.id.tvDevice);
            imgView = itemView.findViewById(R.id.imageView);
            linearLayoutAll = itemView.findViewById(R.id.LinearLayoutAll);
        }
    }
}
