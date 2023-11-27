package com.example.prm392_lab5_recycleview_v1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    private ArrayList<User> userArrayList;
    EditText etUsername, etFullname, etEmail;
    Button btnSave;

    public UserAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.row_userlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = userArrayList.get(position);

        holder.tvUserName.setText("Username: "+user.getUsername());
        holder.tvFullName.setText("Fullname: "+user.getFullname());
        holder.tvEmail.setText("Email: "+user.getEmail());

        holder.linearLayoutAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_user);

                // ánh xạ
                etUsername = dialog.findViewById(R.id.etUsername);
                etFullname = dialog.findViewById(R.id.etFullname);
                etEmail = dialog.findViewById(R.id.etEmail);
                btnSave = dialog.findViewById(R.id.btnSave);

                etUsername.setText((userArrayList.get(position)).getUsername());
                etFullname.setText((userArrayList.get(position)).getFullname());
                etEmail.setText((userArrayList.get(position)).getEmail());


                btnSave.setText("Update");
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username="", fullname="", email="";
                        if(!etUsername.getText().toString().equals("")){
                            username = etUsername.getText().toString();
                        }else {
                            Toast.makeText(context, "Please enter Username", Toast.LENGTH_SHORT).show();
                        }
                        if(!etFullname.getText().toString().equals("")){
                            fullname = etFullname.getText().toString();
                        }else {
                            Toast.makeText(context, "Please enter Fullname", Toast.LENGTH_SHORT).show();
                        }
                        if(!etEmail.getText().toString().equals("")){
                            email = etEmail.getText().toString();
                        }else {
                            Toast.makeText(context, "Please enter Email", Toast.LENGTH_SHORT).show();
                        }
                        userArrayList.set(position, new User(username, fullname, email));
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
                        .setTitle("Delete User")
                        .setMessage("Are you sure want to delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userArrayList.remove(position);
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
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserName, tvFullName, tvEmail;
        LinearLayout linearLayoutAll;

        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            // Ánh xạ
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvFullName =  itemView.findViewById(R.id.tvFullName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            linearLayoutAll = itemView.findViewById(R.id.linearLayoutAll);
        }
    }
}
