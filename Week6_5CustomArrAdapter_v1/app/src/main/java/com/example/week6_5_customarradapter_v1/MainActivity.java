package com.example.week6_5_customarradapter_v1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String[] OSes ={"Android", "IOS", "Microsoft", "HarmonyOS"};
    private ArrayAdapter<String> mArrAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv_os);

        lv = (ListView) findViewById(R.id.lv_os);

        mArrAdapter = new OsArrAdapter(this, OSes);
        lv.setAdapter(mArrAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.label);
                ImageView iv = (ImageView) view.findViewById(R.id.logo);

                Toast.makeText(MainActivity.this, tv.getText().toString() + " is clicked", Toast.LENGTH_SHORT).show();

                tv.setText(tv.getText().toString()+ "*");
                iv.setImageResource(R.drawable.ic_launcher_background);
            }
        });
    }
}