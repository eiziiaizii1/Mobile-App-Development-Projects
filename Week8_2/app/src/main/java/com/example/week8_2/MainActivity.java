package com.example.week8_2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Company> dataArray = new ArrayList<Company>();
    AdapterView.AdapterContextMenuInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prepareData();

        listView = findViewById(R.id.listView);

        listView.setAdapter(new CustomCompanyAdapter(dataArray, this));

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View view, int position,
                                    long id) {

                String str1 = (String) ((TextView) view.findViewById(R.id.ceo))
                        .getText();
                String str2 = (String) ((TextView) view
                        .findViewById(R.id.company)).getText();
                String temp = "CEO: " + str1 + "\n" + str2;

                Toast.makeText(getBaseContext(), temp, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void prepareData() {

        Company prepare_data;

        prepare_data = new Company(R.drawable.google, "Larry Page", "Google",
                "CEO of Google", "30/10/2013 15:40");
        dataArray.add(prepare_data);

        prepare_data = new Company(R.drawable.apple, "Timothy D. Cook",
                "Apple", "CEO of Apple.", "30/10/2013 15:40");
        dataArray.add(prepare_data);

        prepare_data = new Company(R.drawable.microsoft, "Steve Ballmer",
                "Microsoft", "CEO of Microsoft.", "30/10/2013 15:40");
        dataArray.add(prepare_data);
    }
}