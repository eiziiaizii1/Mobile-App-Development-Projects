package com.example.q2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CurrencyCustomAdapter extends ArrayAdapter<CurrencyListData> {

    private Context context;
    private ArrayList<CurrencyListData> data;

    public CurrencyCustomAdapter(@NonNull Context context, ArrayList<CurrencyListData> data) {
        super(context, R.layout.currency_list_item,data);
        this.context =context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        if(rowView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.currency_list_item, null);
        }
        TextView tv = rowView.findViewById(R.id.tv_currencyListItem);
        ImageView iv = rowView.findViewById(R.id.left_pic);

        CurrencyListData temp =  data.get(position);
        tv.setText(temp.getCountry());
        iv.setImageResource(temp.getImage());

        return rowView;
    }
}
