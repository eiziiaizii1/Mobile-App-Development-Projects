package com.example.week10_5;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private GestureDetector mDetector;
    private ImageView mImageView;
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

        mDetector = new GestureDetector(this, new MyGestureListener());
        mImageView = findViewById(R.id.img1);
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return MainActivity.this.mDetector.onTouchEvent(motionEvent);
            }
        });
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        /*
         * Notified when a tap occurs with the down MotionEvent that triggered
         * it. This will be triggered immediately for every down event. All
         * other events should be preceded by this.
         */

        @Override
        public boolean onDown(MotionEvent event) {
            // tv.setText("onDown: " + event.toString());
            return true;
        }

        /* Notified when a single-tap occurs. */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Toast.makeText(getBaseContext(), "ImageView SingleTap gesture",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        /* Notified when a double-tap occurs. */
        @Override
        public boolean onDoubleTap(MotionEvent event) {
            Toast.makeText(getBaseContext(), "ImageView DoubleTap gesture",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}