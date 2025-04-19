package com.example.week8_4;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private boolean isDefaultSelection = true;
    private String[] spinnerValues = { "Facebook", "X", "Gmail" };
    private Spinner mSpinner;
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

        mSpinner = (Spinner) findViewById(R.id.spinnerSocial);
        mSpinner.setAdapter(new MyAdapter(this, R.layout.spinner_layout,
                spinnerValues));

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int index, long l) {

                if (isDefaultSelection)
                    isDefaultSelection = false;
                else
                    displayToast(spinnerValues[index] + " is selected ");

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    private void displayToast(String msg) {

        Toast toast = Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT);
        // Appears in the center
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        // Appears in the top-left corner
        // toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        toast.show();

        // To use Toast in one go
        // Appear in the center bottom by default
        // Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}