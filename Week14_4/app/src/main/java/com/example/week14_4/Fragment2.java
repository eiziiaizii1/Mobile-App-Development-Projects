package com.example.week14_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

public class Fragment2 extends Fragment {
    private TextView lblFragment1;
    private Button btnGetText;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);
        lblFragment1 = view.findViewById(R.id.lblFragment2);
        Button btnGetText = view.findViewById(R.id.btnGetText);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getText().observe(getViewLifecycleOwner(), text -> {
            lblFragment1.setText(text);
        });

        // Set up the button click listener
        btnGetText.setOnClickListener(v -> {
            Toast.makeText(requireContext(), lblFragment1.getText(), Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
