package com.example.week10_3;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
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

        mDetector = new GestureDetectorCompat(this,this);

        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);

        tv = (TextView) findViewById(R.id.tv);

    }

    /* Called when a touch screen event was not handled by any of the views under
           it. This is most useful to process touch events that happen outside of your
           window bounds, where there is no view to receive it. */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }


    /* Notified when a tap occurs with the down MotionEvent that triggered it. This
       will be triggered immediately for every down event. All other events should
       be preceded by this. */
    public boolean onDown(MotionEvent event) {
        tv.setText("onDown: " + event.toString());
        return true;
    }


    /* Notified of a fling event when it occurs with the initial on down MotionEvent
       and the matching up MotionEvent. The calculated velocity is supplied along
       the x and y axis in pixels per second. */
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        tv.setText("onFling: " + event1.toString() + event2.toString());
        return true;
    }

    /* Notified when a long press occurs with the initial on down MotionEvent
       that triggered it. */
    public void onLongPress(MotionEvent event) {
        tv.setText("onLongPress: " + event.toString());
    }

    /* Notified when a scroll occurs with the initial on down MotionEvent and the
       current move MotionEvent. The distance in x and y is also supplied for
       convenience. */
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        tv.setText("onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    /* The user has performed a down MotionEvent and not performed a move or up yet.
       This event is commonly used to provide visual feedback to the user to let them
       know that their action has been recognized i.e. highlight an element. */
    public void onShowPress(MotionEvent event) {
        tv.setText("onShowPress: " + event.toString());
    }

    /* Notified when a tap occurs with the up MotionEvent that triggered it. */
    public boolean onSingleTapUp(MotionEvent event) {
        tv.setText("onSingleTapUp: " + event.toString());
        return true;
    }

    /* Notified when a double-tap occurs. */
    public boolean onDoubleTap(MotionEvent event) {
        tv.setText("onDoubleTap: " + event.toString());
        return true;
    }

    /* Notified when an event within a double-tap gesture occurs, including the
       down, move, and up events. */
    public boolean onDoubleTapEvent(MotionEvent event) {
        tv.setText("onDoubleTapEvent: " + event.toString());
        return true;
    }

    /* Notified when a single-tap occurs. */
    public boolean onSingleTapConfirmed(MotionEvent event) {
        tv.setText("onSingleTapConfirmed: " + event.toString());
        return true;
    }
}