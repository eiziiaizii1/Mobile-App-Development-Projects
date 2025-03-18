package com.example.q2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MyListArrayAdapter1 extends ArrayAdapter<String> {

    private String[] values;
    private Context context;
    public MyListArrayAdapter1(Context context, String[] data) {
        super(context, R.layout.list_item, data);
        this.context = context;
        this.values = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item,parent,false);
        }
        TextView tv = rowView.findViewById(R.id.tv_listitem);

        tv.setText(values[position]);

        return rowView;
    }
}
