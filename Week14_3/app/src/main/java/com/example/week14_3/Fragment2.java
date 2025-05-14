package com.example.week14_3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private static final String TAG = "Fragment 2";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView in F2");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false);
    }
}