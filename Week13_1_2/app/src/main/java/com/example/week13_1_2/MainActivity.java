package com.example.week13_1_2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bumptech.glide.Glide;
import com.example.week13_1_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String imageUrl = "https://aybu.edu.tr/GetFile?id=528368f5-fdce-4119-9777-cd1da02b839d.jpg";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater()); // initialize binding
        setContentView(binding.getRoot()); // use binding's root view

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btn.setOnClickListener(v -> {
            binding.btn.setEnabled(false);

            executor.execute(() -> {
                try {
                    Thread.sleep(3000); // Simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(() -> Glide.with(MainActivity.this)
                        .load(imageUrl)
                        .into(binding.img));
            });
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}