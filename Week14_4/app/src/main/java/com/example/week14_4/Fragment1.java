package com.example.week14_4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment 2";
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment1, container, false);

        TextView lblFragment1 = view.findViewById(R.id.lblFragment1);
        Button btnSendText = view.findViewById(R.id.btnSendText);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        btnSendText.setOnClickListener(v -> {
            sharedViewModel.setText(lblFragment1.getText().toString());
        });

        return view;
    }
}