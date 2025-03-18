package com.example.q2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class BmiActivity extends Activity {

    EditText kg_et,cm_et;
    Button calc_btn,clr_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.bmi);

        kg_et = findViewById(R.id.et_weight);
        cm_et = findViewById(R.id.et_height);
        calc_btn = findViewById(R.id.btnCalculate);
        clr_btn = findViewById(R.id.btnClear);

    }

    public void onClick(View view) {

        if(view.getId() == calc_btn.getId()){
            double h = Double.parseDouble(cm_et.getText().toString());
            double w = Double.parseDouble(kg_et.getText().toString());

            double result = w / h * h;

            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("BMI RESULT");
            ad.setIcon(R.drawable.bmi);
            ad.setMessage("BMI value = " + String.format("%.2f", result));

            ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            ad.create();
            ad.show();
        }
        else if(view.getId() == clr_btn.getId()){
            kg_et.setText("");
            cm_et.setText("");
        }
    }
}
