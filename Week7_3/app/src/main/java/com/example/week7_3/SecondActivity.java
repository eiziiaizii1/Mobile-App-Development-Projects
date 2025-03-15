package com.example.week7_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    private String[] numbers = {"One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten" };
    private int num1, num2, result;
    private TextView tvResult;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        tvResult = (TextView) findViewById(R.id.tvRes);

        // getIntent() method obtain the intent that started the activity.
        // getExtras() method obtain the Bundle object that is send from calling
        // activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            num1 = extras.getInt("num1");
            num2 = extras.getInt("num2");
            result = num1 + num2;

            if ((num1 <= 0 || num1 > 10) || (num2 <= 0 || num2 > 10))
                msg = "Wrong numbers";
            else
                msg = numbers[num1 - 1] + " + " + numbers[num2 - 1] + " = "
                        + result;

            tvResult.setText(msg);
        }
    }

    // When user press on OK button, msg content will be returned
    public void onClick(View view) {

        /*
         * In order for an activity to return a value to the calling activity,
         * you use an Intent object to send data back via setData() method
         */
        Intent dataToSentPreviousActivity = new Intent();

        //dataToSentPreviousActivity.setData(Uri.parse(msg));
        Bundle returnBundle = new Bundle();
        returnBundle.putString("answer", msg);
        dataToSentPreviousActivity.putExtras(returnBundle);

        /*
         * RESULT_OK and data (intent object) returned back to the calling
         * activity.
         */
        setResult(RESULT_OK, dataToSentPreviousActivity);

        // Closes the current activity and returns to the calling activity
        finish();

    }

}
