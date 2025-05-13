package com.example.week10_4;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private GestureDetectorCompat mDetector;
    private TextView tv;
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
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        tv = (TextView) findViewById(R.id.tv);
    }

    public void onClick(View view) {
        tv.setText("");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        /* Notified when a tap occurs with the down MotionEvent that triggered it. This
        will be triggered immediately for every down event. All other events should
        be preceded by this. */

        /* Notified when a single-tap occurs. */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            tv.setText("onSingleTapConfirmed: " + event.toString());
            return true;
        }

        /* Notified when a double-tap occurs. */
        @Override
        public boolean onDoubleTap(MotionEvent event) {
            tv.setText("onDoubleTap: " + event.toString());
            return true;
        }
    }
}