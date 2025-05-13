package com.example.week10_0;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button selectDateBtn, selectDateRangeBtn;

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

        selectDateBtn = findViewById(R.id.selectDateBtn);
        selectDateRangeBtn = findViewById(R.id.selectDateRangeBtn);

        // 🔹 Single Date Picker 📅
        selectDateBtn.setOnClickListener(view -> {
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText("Select a Date");
            MaterialDatePicker<Long> datePicker = builder.build();

            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");

            datePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<Long>) selection -> {
                String selectedDate = formatDate(selection);
                Toast.makeText(this, "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
            });
        });

        // 🔸 Date Range Picker 📅
        selectDateRangeBtn.setOnClickListener(view -> {
            MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
            builder.setTitleText("Select a Date Range");
            MaterialDatePicker<Pair<Long, Long>> dateRangePicker = builder.build();

            dateRangePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER");

            dateRangePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>) selection -> {
                Long start = selection.first;
                Long end = selection.second;
                String message = "Start: " + formatDate(start) + "\nEnd: " + formatDate(end);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            });
        });
    }

    // 📅 Format timestamp into readable string
    private String formatDate(Long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return sdf.format(new Date(millis));
    }
}