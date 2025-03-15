package com.example.week7_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    private Intent intent;
    private ActivityResultLauncher<Intent> secondActivityLauncher;
    private int requestCode = 1;

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
        Log.d("onCreate","ok");
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        secondActivityLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if(result.getResultCode() == MainActivity.RESULT_OK && result.getData() != null){
                                /*Intent data = result.getData();
                                String message = data != null ? data.getStringExtra("result") : "No data";*/
                                Bundle bundle = result.getData().getExtras();
                                String message = bundle.getString("answer");
                                Toast.makeText(this, "Received: " + message, Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
                        });
    }

    public void onClick(View view) {
        intent = new Intent(this, SecondActivity.class);

        /*
         * Use Bundle object to carry data to the target activity Bundle object
         * is basically a dictionary object that enables you to set data in
         * key/value pairs.
         */

        Bundle mBundle = new Bundle();
        mBundle.putInt("num1", Integer.parseInt(et1.getText().toString()));
        mBundle.putInt("num2", Integer.parseInt(et2.getText().toString()));

        intent.putExtras(mBundle);

        /*
         * To call activity and wait for result to be returned from it. It also
         * pass request code as integer value that identifies an activity you
         * are calling. This is needed because when an activity returns a value,
         * you must have a way to identify it. For example, you may be calling
         * multiple activities at the same time and some activities may not
         * return immediately (for example, waiting for a reply from a server).
         * When an activity returns, you need this request code to determine
         * which activity is actually returned.
         */
        //startActivityForResult(intent, request_Code);

        secondActivityLauncher.launch(intent);
    }

    /*
     * In the calling activity, implement onActivityResult(...) method, which is
     * called whenever an activity returns the returned result is passed in via
     * the data argument and you obtain its details through the getData() method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK)
                Toast.makeText(this, data.getData().toString(),Toast.LENGTH_SHORT).show();
        }
    }*/
}