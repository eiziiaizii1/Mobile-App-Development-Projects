package com.example.week5_3;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Resources mResources;
    private Configuration mConfiguration;
    private int orientation;

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

        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            displayToast("Portrait mode");
        else
            displayToast("Landscape mode");
    }

    private void displayToast(String tMsg) {
        Toast t = Toast.makeText(getBaseContext(),tMsg,Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }


    public void onClick(View view) {

        if(view.getId() == R.id.btn1){
            displayToast("Top left btn is clicked");
        } else if (view.getId() == R.id.btn2) {
            displayToast("Top right btn is clicked");
        } else if (view.getId() == R.id.btn3) {
            displayToast("Bottom left btn is clicked");
        } else if (view.getId() == R.id.btn4) {
            displayToast("Bottom left btn is clicked");
        }else if (view.getId() == R.id.btn5) {
        displayToast("Center btn is clicked");
        } else if (view.getId() == R.id.btn6) {
            displayToast("Top Center btn is clicked");
        } else if (view.getId() == R.id.btn7) {
            displayToast("Bottom Center btn is clicked");
        }

    }
}