package com.example.week8_5;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Dialog customDialog;
    private EditText firstName;
    private EditText lastName;
    private TextView mTextView;
    private Button saveBtn;
    private Button cancelBtn;
    private String nameStr, lastNameStr;
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
        mTextView = findViewById(R.id.tv1);
    }

    public void onClick(View view) {

        customDialog = new Dialog(MainActivity.this);
        customDialog.setContentView(R.layout.dialog);

        firstName = customDialog.findViewById(R.id.firstname);
        lastName = customDialog.findViewById(R.id.lastname);

        saveBtn = customDialog.findViewById(R.id.savebtn);
        cancelBtn = customDialog.findViewById(R.id.cancelbtn);

        customDialog.setTitle("Custom Dialog");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                nameStr = firstName.getText().toString();
                lastNameStr = lastName.getText().toString();
                mTextView.setText("Your Name is " + nameStr + " " + lastNameStr);
                customDialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                customDialog.dismiss();
            }
        });
        customDialog.show();
        customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

}