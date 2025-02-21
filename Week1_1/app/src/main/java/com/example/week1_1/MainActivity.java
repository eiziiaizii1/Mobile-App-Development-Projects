package com.example.week1_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

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
    }

    public void convertCurrency(View view) {
        EditText editText =findViewById(R.id.dollarText);
        TextView tlTextView = findViewById(R.id.resultText);

        if(editText.getText().toString().isEmpty()){
            tlTextView.setText(R.string.no_value_message);
            return;
        }

        double tlRate = 36.2065;
        double tlValue = tlRate * Double.parseDouble(editText.getText().toString());

        tlTextView.setText(String.format(Locale.ENGLISH,"%.2f",tlValue));






//        EditText dollarText = findViewById(R.id.dollarText);
//        TextView liraTextView = findViewById(R.id.resultText);
//
//        if(dollarText.getText().toString().isEmpty()){
//            liraTextView.setText(R.string.no_value_message);
//            return;
//        }
//        double exchangeRate = 36.2063;
//        double turkishLira = Double.parseDouble(dollarText.getText().toString()) * exchangeRate;
//        liraTextView.setText(String.format(Locale.ENGLISH, "%.2f", turkishLira));
    }
}