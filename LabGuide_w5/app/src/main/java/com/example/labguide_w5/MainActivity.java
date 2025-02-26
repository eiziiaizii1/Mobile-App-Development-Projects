package com.example.labguide_w5;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;

    Random random;
    private ValueAnimator colorAnim;
    private TextView tv;
    private static final int RED = 0xffFF0000;
    private static final int BLUE = 0xff0000FF;

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private  int currentImgInd;
    ImageView[] images;


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
        img1 = findViewById(R.id.imv1);
        img2 = findViewById(R.id.imv2);
        img3 = findViewById(R.id.imv3);

        images = new ImageView[]{img1, img2, img3};

        tv = findViewById(R.id.movies_tv);
        mainLayout =findViewById(R.id.main);
        random = new Random();

        colorAnim = ObjectAnimator.ofInt(tv, "textColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

    }

    public void onClick(View view) {
        if(view.getId() == R.id.btnC){
            mainLayout.setBackgroundColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        } else if (view.getId() == R.id.btnL) {
            images[currentImgInd].setVisibility(View.INVISIBLE);
            currentImgInd = (currentImgInd - 1 + images.length) % images.length;
            images[currentImgInd].setVisibility(View.VISIBLE);
        }else if(view.getId() == R.id.btnR){
            images[currentImgInd].setVisibility(View.INVISIBLE);
            currentImgInd = (currentImgInd+1) % images.length;
            images[currentImgInd].setVisibility(View.VISIBLE);
        }
    }
}