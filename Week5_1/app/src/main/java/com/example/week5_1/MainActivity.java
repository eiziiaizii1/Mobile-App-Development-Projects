package com.example.week5_1;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView img1;
    private Button btnC;
    private TextView tv;
    private Animation textBlink;
    private boolean changeImgB=true;
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
        img1 = findViewById(R.id.iv2);
        btnC = findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv1);

        // Code for text blinking
        textBlink = new AlphaAnimation(0.0f, 1.0f);
        textBlink.setDuration(50);
        textBlink.setStartOffset(20);
        textBlink.setRepeatMode(Animation.REVERSE);
        textBlink.setRepeatCount(Animation.INFINITE);
        tv.startAnimation(textBlink);

    }

    public void onClick(View view) {
        changeImgB = !changeImgB;

        if(!changeImgB){
            img1.setVisibility(View.INVISIBLE);
            btnC.setText("Scream");
        }
        else {
            img1.setVisibility(View.VISIBLE);
            btnC.setText("shut up");
        }
    }
}