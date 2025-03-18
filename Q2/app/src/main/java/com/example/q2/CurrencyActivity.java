package com.example.q2;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CurrencyActivity  extends Activity {
    ArrayList<CurrencyListData> dataArray;
    ListView lv;
    AdapterView.AdapterContextMenuInfo info;
    private int currencyPos = 0;
    private TextView tv,tvtitle1,tvtitle2;
    private EditText currency1EditText;
    private EditText currency2EditText;
    private ValueAnimator colorAnim;
    private static final int PINK = 0xffFFFF00;
    private static final int YELLOW = 0xffFA58F4;
    private double currency1Amount = 0.0; // Currency1 amount entered by the user

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.currency_activity);

        tvtitle1 = (TextView) findViewById(R.id.tv_title1);
        tvtitle2 = (TextView) findViewById(R.id.tv_title2);
        tv = (TextView) findViewById(R.id.currency2TextView);

        colorAnim =  ObjectAnimator.ofInt(tvtitle1,"textColor",YELLOW,PINK);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        colorAnim = ObjectAnimator.ofInt(tvtitle2,"textColor",PINK,YELLOW);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        lv = findViewById(R.id.listView);
        dataArray = new ArrayList<CurrencyListData>();

        // Single object
        CurrencyListData prepare_data;
        prepare_data = new CurrencyListData();
        prepare_data.setImage(R.drawable.usa);
        prepare_data.setCountry("USD");
        dataArray.add(prepare_data);

        prepare_data = new CurrencyListData();
        prepare_data.setImage(R.drawable.eu);
        prepare_data.setCountry("EUR");
        dataArray.add(prepare_data);

        prepare_data = new CurrencyListData();
        prepare_data.setImage(R.drawable.uk);
        prepare_data.setCountry("GBP");
        dataArray.add(prepare_data);

        prepare_data = new CurrencyListData();
        prepare_data.setImage(R.drawable.japan);
        prepare_data.setCountry("YEN");
        dataArray.add(prepare_data);


        currency1EditText = (EditText) findViewById(R.id.currency1EditText);
        currency1EditText.addTextChangedListener(currency1EditTextWatcher);

        currency2EditText = (EditText) findViewById(R.id.currency2EditText);
        // Make the bottom field non-editable
        currency2EditText.setFocusable(false);
        currency2EditText.setEnabled(false);
        // You can also change the background to make it look read-only
        currency2EditText.setBackgroundResource(android.R.drawable.editbox_background_normal);


        lv.setAdapter(new CurrencyCustomAdapter(CurrencyActivity.this, dataArray));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        currencyPos = 0;
                        tv.setText("Amount (USD): ");
                        break;
                    case 1:
                        currencyPos = 1;
                        tv.setText("Amount (EUR): ");
                        break;
                    case 2:
                        currencyPos = 2;
                        tv.setText("Amount (GBP): ");
                        break;
                    case 3:
                        currencyPos = 3;
                        tv.setText("Amount (YEN): ");
                        break;

                }

                initialize();

                // If the top field already has a value, update the bottom field
                if (!currency1EditText.getText().toString().isEmpty()) {
                    try {
                        currency1Amount = Double.parseDouble(currency1EditText.getText().toString());
                        update2();
                    } catch (NumberFormatException e) {
                        currency1Amount = 0.0;
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    private TextWatcher currency1EditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                currency1Amount = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                currency1Amount = 0.0; // default if an exception occurs
            }
            // UPDATE THE 2ND INACTIVE EDIT TEXT
            update2();
        }

    };

    private void update2(){
        if(!currency1EditText.getText().toString().isEmpty()){
            switch (currencyPos){
                case 0:
                    currency2EditText.setText(String.format("%.2f",fromTL2USD(currency1Amount)));
                    break;
                case 1:
                    currency2EditText.setText(String.format("%.2f",fromTL2EUR(currency1Amount)));
                    break;
                case 2:
                    currency2EditText.setText(String.format("%.2f", fromTL2GBP(currency1Amount)));
                    break;
                case 3:
                    currency2EditText.setText(String.format("%.2f", fromTL2YEN(currency1Amount)));
                    break;
                default:
                    currency2EditText.setText("");
            }
        }
    }

    private void initialize(){
        currency1EditText.removeTextChangedListener(currency1EditTextWatcher);

        currency1EditText.setText("");
        currency1EditText.setSelection(currency1EditText.getText().length());

        currency2EditText.setText("");

        currency1EditText.addTextChangedListener(currency1EditTextWatcher);

        currency1EditText.addTextChangedListener(currency1EditTextWatcher);
    }


    // Converting TL to USD
    private double fromTL2USD(double amount) {
        return (amount / 36.4);
    }

    // Converting TL to EUR
    private double fromTL2EUR(double amount) {
        return (amount / 38.9);
    }

    // Converting TL to GBP
    private double fromTL2GBP(double amount) {
        return (amount / 44.6);
    }

    // Converting TL to YEN
    private double fromTL2YEN(double amount) {
        return (amount / 0.24);
    }

}
