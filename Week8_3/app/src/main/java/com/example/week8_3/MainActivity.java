package com.example.week8_3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {
    private ExpandableListAdapter mExpandableListAdapter;
    private ExpandableListView mExpandableListView;

    // Group/parent data
    private List<String> listDataHeader;

    // HashMap is a a data structure, based on hashing, which allows one to
    // store object as key value pair, advantage of using HashMap is that, one
    // can retrieve object on constant time i.e. O(1), if one knows the key.
    private HashMap<String, List<String>> listDataChild;

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

        mExpandableListView = (ExpandableListView) findViewById(R.id.explv1);

        // Preparing list data
        prepareListData();

        mExpandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // Setting list adapter
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnChildClickListener(this);
    }

    // Preparing the list data
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding group/parent data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child1 data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        // Adding child2 data
        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Mikey 17");
        nowShowing.add("Snow White");
        nowShowing.add("Paddington");
        nowShowing.add("Terrifier 2");
        nowShowing.add("Maria");
        nowShowing.add("The Monkey");

        // Adding child3 data
        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Minecraft");
        comingSoon.add("Exorcism Chronicles");
        comingSoon.add("Lillian Hall");
        comingSoon.add("Armand");
        comingSoon.add("Stelios");

        // Header, Child data
        listDataChild.put(listDataHeader.get(0), top250);
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }


    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        TextView mTextView = view.findViewById(R.id.tvListItem);
        Toast.makeText(getBaseContext(), mTextView.getText().toString(), Toast.LENGTH_SHORT).show();
        return false;
    }
}