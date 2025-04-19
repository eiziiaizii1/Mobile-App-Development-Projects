package com.example.week8_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomCompanyAdapter extends BaseAdapter {
    private ArrayList<Company> data;
    private Context context;

    public CustomCompanyAdapter(ArrayList<Company> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;

        if (rowView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        ImageView image = rowView.findViewById(R.id.image);
        TextView ceoView = rowView.findViewById(R.id.ceo);
        TextView companyView = rowView.findViewById(R.id.company);
        TextView descView = rowView.findViewById(R.id.description);
        TextView timeView = rowView.findViewById(R.id.time);

        // Get individual object from  ArrayList<ListData> and set ListView items
        Company temp_data = data.get(i);
        image.setImageResource(temp_data.getImage());
        ceoView.setText(temp_data.getCeo());
        companyView.setText("Company: " + temp_data.getCompany());
        descView.setText(temp_data.getDesc());
        timeView.setText(temp_data.getTime());

        return rowView;
    }
}