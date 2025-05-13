package com.example.week11_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String prefName = "MyPref";    // XML file name
    private EditText mEditText;
    private SeekBar mSeekBar;
    private float fontSize;

    private static final String FONT_SIZE_KEY = "fontsize";
    private static final String TEXT_VALUE_KEY = "textvalue";

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

        mEditText = findViewById(R.id.et1);
        mSeekBar = findViewById(R.id.Seek1);

        // Load the SharedPreferences object
        // The MODE_PRIVATE constant indicates that the shared preference
        // file can only be opened by the application that created it
        mSharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);

        // Set the font size to the previously saved values
        // If there is no previously saved value set the value to 15
        fontSize = mSharedPreferences.getFloat(FONT_SIZE_KEY, 15);

        // Initialize the SeekBar and EditText
        mSeekBar.setProgress((int) fontSize);
        mEditText.setText(mSharedPreferences.getString(TEXT_VALUE_KEY, ""));
        mEditText.setTextSize(fontSize);
        mSeekBar.setOnSeekBarChangeListener(mySeekBarListener);
    }

    public void onClick(View v) {
        // Interface used for modifying values in a SharedPreferences object
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        // Save the values in the EditText view to preferences
        // Store the current progress value from SeekBar, not getTextSize() which returns pixels
        editor.putFloat(FONT_SIZE_KEY, mSeekBar.getProgress());
        editor.putString(TEXT_VALUE_KEY, mEditText.getText().toString());

        // Saves the values (Must be called)
        editor.commit();

        // Display file saved message
        displayToast("Text and Font size saved successfully!");
    }

    private void displayToast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    // Called when the user changes the weight value of SeekBar
    private SeekBar.OnSeekBarChangeListener mySeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        // Update currentCustomPercent, then call updateCustom
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // Change the font size of the EditText
            mEditText.setTextSize(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Not used, but required to implement
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // Not used, but required to implement
        }
    };
}

//package com.example.week11_1;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.SeekBar;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class MainActivity extends AppCompatActivity {
//
//    private SharedPreferences mSharedPreferences;
//    private String prefName = "MyPref";		// XML file name
//    private EditText mEditText;
//    private SeekBar mSeekBar;
//    private float fontSize;
//
//    private static final String FONT_SIZE_KEY = "fontsize";
//    private static final String TEXT_VALUE_KEY = "textvalue";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        mEditText = findViewById(R.id.et1);
//        mSeekBar = findViewById(R.id.Seek1);
//
//
//        // Load the SharedPreferences object
//        // The MODE_PRIVATE constant indicates that the shared preference
//        // file can only be opened by the application that created it
//        mSharedPreferences = getSharedPreferences(prefName,MODE_PRIVATE);
//
//        // Can also use this format
//        //mSharedPreferences = getPreferences(MODE_PRIVATE);
//
//        // Set the font size to the previously saved values
//        // If there is no previously saved value set the value to 15
//        fontSize = mSharedPreferences.getFloat(FONT_SIZE_KEY, 15);
//
//        // Initialize the SeekBar and EditText
//        mSeekBar.setProgress((int) fontSize);
//        mEditText.setText(mSharedPreferences.getString(TEXT_VALUE_KEY, ""));
//        mEditText.setTextSize(mSeekBar.getProgress());
//        mSeekBar.setOnSeekBarChangeListener(mySeekBarListener);
//    }
//
//    public void onClick(View v) {
//        // Get the SharedPreferences object
//        //mSharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
//        //mSharedPreferences = getPreferences(MODE_PRIVATE);
//
//        // Interface used for modifying values in a SharedPreferences object
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//
//        // Save the values in the EditText view to preferences
//        editor.putFloat(FONT_SIZE_KEY, mEditText.getTextSize());
//        editor.putString(TEXT_VALUE_KEY, mEditText.getText().toString());
//
//        // Saves the values (Must be called)
//        editor.commit();
//
//        // Display file saved message
//        displayToast("Text and Font size saved successfully!");
//    }
//
//    private void displayToast(String msg) {
//        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
//    }
//
//    // Called when the user changes the weight value of SeekBar
//    private SeekBar.OnSeekBarChangeListener mySeekBarListener = new SeekBar.OnSeekBarChangeListener() {
//        // Update currentCustomPercent, then call updateCustom
//        @Override
//        public void onProgressChanged(SeekBar seekBar, int progress,
//                                      boolean fromUser) {
//            // Change the font size of the EditText
//            mEditText.setTextSize( (float) progress );
//        }
//
//        @Override
//        public void onStartTrackingTouch(SeekBar seekBar) {
//
//        }
//
//        @Override
//        public void onStopTrackingTouch(SeekBar seekBar) {
//
//        }
//    };
//}