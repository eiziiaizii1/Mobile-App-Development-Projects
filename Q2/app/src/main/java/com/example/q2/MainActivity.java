package com.example.q2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hiding status bar using code
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rg = findViewById(R.id.main_radioGroup);

    }

    public void onClick(View view) {
        int selectedId = rg.getCheckedRadioButtonId(); // Get selected radio button ID

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return; // Prevent crash
        }

        intent = null;
        if (selectedId == R.id.rb1) { // Use IDs from XML instead of `getChildAt()`
            intent = new Intent(this, MyListActivity1.class);
        } else if (selectedId == R.id.rb2) {
            intent = new Intent(this, MyListActivity2.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}