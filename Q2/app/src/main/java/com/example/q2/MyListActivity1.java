package com.example.q2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class MyListActivity1 extends Activity {

    private String[] options ={"Currency Converter", "BMI Calculator"};
    private ArrayAdapter<String> mArrAdapter;
    ListView lv;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                // Hiding title bar using code
                requestWindowFeature(Window.FEATURE_NO_TITLE);

                // Hiding status bar using code
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list_activity1);


        lv = findViewById(R.id.lv1);
        mArrAdapter = new MyListArrayAdapter1(this, options);
        lv.setAdapter(mArrAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Start new activity and pass data
                if(position == 0){
                    intent = new Intent(MyListActivity1.this, CurrencyActivity.class);
                }
                else if(position == 1){
                    intent = new Intent(MyListActivity1.this, BmiActivity.class);
                }

                if(intent != null){
                    startActivity(intent);
                }
            }
        });
    }
}
