package com.example.week7_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    private String [] numbers = { "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten" };
    private int userNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        TextView tv = (TextView) findViewById(R.id.tvRes);

//        Intent intent = getIntent();
//        userNumber= Integer.parseInt(intent.getStringExtra("num"));
//        if (userNumber <= 0 || userNumber > 10)
//            tv.setText(" " + "Wrong Number");
//        else
//            tv.setText(" " + numbers[userNumber - 1]);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            userNumber = Integer.parseInt(extras.getString("num"));
            if (userNumber <= 0 || userNumber > 10)
                tv.setText(" " + "Wrong Number");
            else
                tv.setText(" " + numbers[userNumber - 1]);

        }

    }
}
