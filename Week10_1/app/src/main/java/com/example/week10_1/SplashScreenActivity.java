/* Course: 			Mobile Application Development
 * Project:			SplashScreen
 * AVD:				Nexus 4, Android API 18
 * Description:		A simple program that demonstrates how to create a splash screen
 * URL:				http://developer.android.com/reference/android/os/Handler.html
 * URL:				http://www.vogella.com/articles/AndroidBackgroundProcessing/article.html
 * URL:				http://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html
 */

package com.example.week10_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {
    private Intent intent;

    // Splash screen timer (5 seconds)
    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* postDelayed() method causes the Runnable object to be added to the message queue
         * to be run after the specified amount of time elapses. The runnable will be run on
         * the thread to which this handler is attached.
         */
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show your application logo or company logo
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your application's main activity which is a ListView
                intent = new Intent(SplashScreenActivity.this, MainListActivity.class);

                // Close this activity
                finish();

                startActivity(intent);
            }
        }, SPLASH_TIME_OUT);
    }

}
