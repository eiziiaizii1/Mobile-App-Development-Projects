package com.example.labguide_w6;

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

public class CustomAdapter extends ArrayAdapter<ListData> {
    private ArrayList<ListData> data;
    private Context context;

    CustomAdapter(Context context, ArrayList<ListData> data) {
        super(context, R.layout.list_item, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if(convertView == null){
            LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item,null);
        }
        ImageView iv = rowView.findViewById(R.id.left_pic);
        TextView tv = rowView.findViewById(R.id.tv_listitem);

        ListData tempData = data.get(position);
        iv.setImageResource(tempData.getImage());
        tv.setText(tempData.getCountry());

        return rowView;
    }
}
