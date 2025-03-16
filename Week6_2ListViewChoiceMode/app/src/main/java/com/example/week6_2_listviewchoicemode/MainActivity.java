package com.example.week6_2_listviewchoicemode;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends ListActivity {

    String[] cities = { "Istanbul", "Ankara", "Izmir", "Antalya",
            "Konya", "Izmit", "Bursa", "Kayseri", "Malatya", "Trabzon",
            "Antakya", "Erzurum", "Van" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        ListView lv = getListView();
//        lv.setChoiceMode(ListView.CHOICE_MODE_NONE);
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv.setTextFilterEnabled(true);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, cities));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(this, cities[position] + "is selected", Toast.LENGTH_SHORT).show();
    }
}