package com.example.week7_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNum;
    private Intent intent;
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

        etNum = findViewById(R.id.et);
    }

    public void onClick(View view) {
        /* Intent supports four ways to pass data:
         * 1. Direct: Put data into an Intent directly
         * 2. Bundle: Create a bundle and set the data here
         * 3. Parcelable: It is a way of "serializing" our object.
         * 4. Serializable: Another way to "serialize" data. Its performance is
         * 					slower that Parcelable.
         *
         * First two options are used for passing simple data types (int, double, String etc)
         * For complex data i.e., objects of a class third and fourth option should be used
         */

        // ********  Direct data passing *********
//        intent = new Intent(this,SecondActivity.class);
//        intent.putExtra("num", etNum.getText().toString());
//        startActivity(intent);

        // ******** Data passing using a Bundle *********
        intent = new Intent(this, SecondActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("num", etNum.getText().toString());
        intent.putExtras(mBundle);
        startActivity(intent);
    }
}