package com.example.q2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyListActivity2 extends Activity {

    private String[] options ={"Currency Converter", "BMI Calculator"};
    private ArrayAdapter<String> mArrAdapter;
    private Intent intent;
    ListView lv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                // Hiding title bar using code
                requestWindowFeature(Window.FEATURE_NO_TITLE);

                // Hiding status bar using code
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list_activity2);

        lv = findViewById(R.id.lv2);
        mArrAdapter = new CustomAdapter(this,options);
        lv.setAdapter(mArrAdapter);

        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.header, lv, false);
        lv.addHeaderView(header);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                // Start new activity and pass data
                if(position == 1){
                    intent = new Intent(MyListActivity2.this, CurrencyActivity
                            .class);
                }
                else if(position == 2){
                    intent = new Intent(MyListActivity2.this, BmiActivity.class);
                }

                if(intent != null){
                    startActivity(intent);
                }
            }
        });
    }
}
