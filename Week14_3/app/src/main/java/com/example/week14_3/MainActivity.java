package com.example.week14_3;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.week14_3.Fragment1;
import com.example.week14_3.Fragment2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the initial fragment based on screen orientation
        loadInitialFragment();
    }

    private void loadInitialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Get display info to determine orientation
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            // Landscape Mode - Load Fragment1 and Fragment2 Side by Side
            Fragment1 fragment1 = new Fragment1();
            Fragment2 fragment2 = new Fragment2();

            fragmentTransaction.replace(R.id.fragment_container_left, fragment1);
            fragmentTransaction.replace(R.id.fragment_container_right, fragment2);
        } else {
            // Portrait Mode - Load Only Fragment2
            Fragment2 fragment2 = new Fragment2();
            fragmentTransaction.replace(R.id.fragment_container, fragment2);
        }
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}