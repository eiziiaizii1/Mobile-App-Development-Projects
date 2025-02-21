package com.example.week2_1;

import android.content.DialogInterface;
import android.os.Bundle;
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

    private Button dialogMB, toastMB;

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

        toastMB = (Button) findViewById(R.id.toastButton);
        dialogMB = (Button) findViewById(R.id.diaButton);

        toastMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToastMsg("HEY I am a TOAST");
            }
        });

        dialogMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAndShowDialogBox("HEYYOO WASSUP from dialog box");
            }
        });

    }

    private void makeAndShowDialogBox(String dialogButtonIsClicked){
        AlertDialog.Builder md = new AlertDialog.Builder(this);

        md.setTitle("CENG427");
        md.setMessage(dialogButtonIsClicked);
        md.setIcon(R.drawable.ic_launcher_background);

        md.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToastMsg("YES is clicked");
            }
        });

        md.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToastMsg("NO is clicked");
            }
        });

        md.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToastMsg("CANCEL is clicked");
            }
        });

        md.create();
        md.show();
    }

    private void displayToastMsg(String toastButtonIsClicked){
        //Toast toast = Toast.makeText(getBaseContext(), toastButtonIsClicked, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        //toast.show();
        Toast.makeText(getBaseContext(),toastButtonIsClicked,Toast.LENGTH_SHORT).show();
    }


}