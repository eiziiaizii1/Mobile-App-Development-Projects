package com.example.week3;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String tag = "Events";

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
        Log.d(tag,"In onCreate event");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "In onStart event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "In onResume event");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "In onRestart event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "In onPause event");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(tag, "In onStop event");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag,"In onDestroy event");
    }
}