package com.example.game_project;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AmmountAdapter extends BaseAdapter {

    private ArrayList<Ammount> ammounts;
    private Context context;
    private int layout;

    public AmmountAdapter(ArrayList<Ammount> ammounts, Context context, int layout) {
        this.ammounts = ammounts;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return this.ammounts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(this.layout, null);
        EditText etAmmount = view.findViewById(R.id.etAmmount);
        TextView tvHint = view.findViewById(R.id.tvHint);
        tvHint.setText(ammounts.get(i).getHint());
        if (ammounts.get(i).getAmmount() != 0)
            etAmmount.setText(String.valueOf(ammounts.get(i).getAmmount()));
        etAmmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String betAmountStr = etAmmount.getText().toString();
                if (betAmountStr.trim().isEmpty()) {
                    etAmmount.setError("Please enter bet amount");
                    etAmmount.requestFocus();
                    return;
                }
                ammounts.get(i).setAmmount(Integer.parseInt(etAmmount.getText().toString()));
            }
        });
        return view;
    }
}
