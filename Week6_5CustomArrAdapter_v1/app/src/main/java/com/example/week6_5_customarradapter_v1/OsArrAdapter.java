package com.example.week6_5_customarradapter_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OsArrAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] values;
    private LayoutInflater inflater;

    public OsArrAdapter(@NonNull Context context, String[] values) {
        super(context, R.layout.list_item,values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item,parent,false);
        }
        ImageView iv = (ImageView) rowView.findViewById(R.id.logo);
        TextView tv = (TextView) rowView.findViewById(R.id.label);

        tv.setText(values[position]);
        String str = values[position];

        switch (str) {
            case "Microsoft":
                iv.setImageResource(R.drawable.mic_logo);
                break;
            case "IOS":
                iv.setImageResource(R.drawable.ios_logo);
                break;
            case "HarmonyOS":
                iv.setImageResource(R.drawable.hmhw_logo);
                break;
            default:
                iv.setImageResource(R.drawable.android_logo);
                break;
        }

        return rowView;
    }
}
