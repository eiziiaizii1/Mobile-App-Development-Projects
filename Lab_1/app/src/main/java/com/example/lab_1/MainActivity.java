package com.example.lab_1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button dialogBtn;
    private Button toastBtn;
    private String eventTag= "Events";

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
        dialogBtn = (Button) findViewById(R.id.dialogButton);
        toastBtn = (Button) findViewById(R.id.toastButton);

        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast("This is a toast message");
            }
        });

        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAndShowDialogBox("This is an alertDialog");
            }
        });
    }

    private void displayToast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void makeAndShowDialogBox(String msg) {

        AlertDialog.Builder myDialogBox = new AlertDialog.Builder(this);

        // set message, title, and icon
        myDialogBox.setTitle("My Alert Dialog");
        myDialogBox.setMessage(msg);
        myDialogBox.setIcon(R.drawable.ic_launcher_background);

        // Set three option buttons
        myDialogBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // whatever should be done when answering "Yes" goes
                // here
                displayToast("Yes is clicked");
            }
        });

        myDialogBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // whatever should be done when answering "No" goes
                // here
                displayToast("No is clicked");
            }
        });

        myDialogBox.setNeutralButton("Cancel",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // whatever should be done when answering "Cancel" goes
                // here
                displayToast("Cancel is clicked");
            }
        });

        myDialogBox.create();
        myDialogBox.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(eventTag,"I'm in the background now");
    }
}