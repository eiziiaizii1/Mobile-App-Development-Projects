package com.example.week8_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

public class CustomAdapter extends BaseAdapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    // TreeSet provides an implementation of the Set interface that uses a tree for storage
    // Objects are stored in sorted, ascending order
    // http://www.programcreek.com/2009/02/a-simple-treeset-example/
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();
    private ArrayList<String> mData = new ArrayList<String>();
    private LayoutInflater mInflater;

    public CustomAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        //Log.d("TreeSet Data",Integer.toString( mData.size() - 1));
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    // ListView Rows
                    convertView = mInflater.inflate(R.layout.list_item, parent, false);
                    holder.textView = convertView.findViewById(R.id.text);
                    break;
                case TYPE_SEPARATOR:
                    // Section Header
                    convertView = mInflater.inflate(R.layout.header, parent, false);
                    holder.textView = convertView.findViewById(R.id.textSeparator);
                    break;
            }
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.textView.setText(mData.get(position));

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
}