package com.example.week10_1;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;

public class MainListActivity extends ListActivity {
    private ArrayList<String> applications = new ArrayList<String>();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Adding data to ArrayList
        applications.add("Activity 1");
        applications.add("Activity 2");
        applications.add("Activity 3");

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, applications));
    }

    public void onListItemClick(ListView lv, View v, int position, long id) {
        switch(position){
            case 0:
                intent = new Intent(this, MainActivity1.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, MainActivity3.class);
                startActivity(intent);
                break;
        }

    }

}