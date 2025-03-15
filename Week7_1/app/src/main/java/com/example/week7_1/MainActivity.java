package com.example.week7_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
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
    }

    public void onClick(View view) {
        //Log.d("onClick", "in onclick");

        // This syntax calls another activity by using its class name.
        // It is used more often to call another activity within a project
        // as we know all class names in our project
        intent = new Intent(this, SecondActivity.class);

        // This syntax requires that intent filter must be defined
        // Intent filter is usually used to call a third party program
        // in your application. For example email, browser
        //intent = new Intent("com.ceng427.week7_1.SecondActivity");

        startActivity(intent);
    }
}