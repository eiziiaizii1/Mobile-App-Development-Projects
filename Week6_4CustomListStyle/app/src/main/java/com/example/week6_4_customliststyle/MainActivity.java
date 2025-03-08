package com.example.week6_4_customliststyle;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> cities = new ArrayList<String>();
    private ArrayAdapter<String> mArrAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareData();

        mListView = (ListView) findViewById(R.id.lv_cities);
        mArrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        mListView.setAdapter(mArrAdapter);

        mArrAdapter.add("ADANA");
        mArrAdapter.add("CORUM");

        mArrAdapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ((TextView)view).getText().toString()+" is selected", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = mListView.getItemAtPosition(position).toString();
                mArrAdapter.remove(selectedItem);
                mArrAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, selectedItem + " is deleted", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void prepareData(){
        cities.add("ANKARA");
        cities.add("ISTANBUL");
    }
}