package com.example.week14_4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Uses a clean layout without <fragment> tags

        // Load the initial fragment based on screen orientation
        //loadInitialFragment();

    }

    /*private void loadInitialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Get display info to determine orientation
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Fragment fragment;
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            // Landscape Mode - Load Fragment1
            fragment = new Fragment1();
        } else {
            // Portrait Mode - Load Fragment2
            fragment = new Fragment2();
        }

        // Replace the fragment dynamically with FragmentContainerView
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }*/
}