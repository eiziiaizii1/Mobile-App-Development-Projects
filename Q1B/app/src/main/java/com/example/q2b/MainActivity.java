package com.example.q2b;

import android.content.DialogInterface;
import android.graphics.Movie;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner movieSpinner;
    private ImageView posterImg_iv;
    private RadioGroup radioGroup;
    private LinearLayout tCountLayout;
    private SeekBar ticketSeekBar;
    private TextView ticketCountText;
    private int ticketCountNum = 0;
    private RadioGroup payment_rg;
    private EditText nameTxt;
    private Button clearBtn;
    private Button finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        posterImg_iv = (ImageView) findViewById(R.id.ivMoviePoster);
        movieSpinner = (android.widget.Spinner) findViewById(R.id.movie_spinner);

        radioGroup = (RadioGroup) findViewById(R.id.radioGrp1);
        tCountLayout = (LinearLayout) findViewById(R.id.ticketCount_layout);
        ticketSeekBar = (SeekBar) findViewById(R.id.ticketCount_sb);
        ticketCountText = (TextView) findViewById(R.id.ticketCount_txt);

        payment_rg = (RadioGroup) findViewById(R.id.radioGrp2);

        nameTxt = (EditText) findViewById(R.id.name_tv);
        clearBtn = (Button) findViewById(R.id.clear_btn);
        finishBtn = (Button) findViewById(R.id.finish_btn);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEverything();
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameTxt.getText().toString().isBlank() || nameTxt.getText().toString().isEmpty()){
                    displayToastMsg("Your name CANNOT be empty!!!");
                } else if (ticketCountNum == 0) {
                    displayToastMsg("Please choose Single Ticket or Multiple Tickets");
                } else{
                    makeAndShowDialogBox(
                            "Movie: " + movieSpinner.getSelectedItem().toString() +
                                    "\nPayment: "+ ((RadioButton) findViewById(payment_rg.getCheckedRadioButtonId()))
                                    .getText().toString() +
                                    "\nTicket Count: "+ ticketCountNum +
                                    "\nYour Name: " + nameTxt.getText().toString()
                            );
                }
            }
        });


        SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String tCountStr = "Ticket count (" + progress + ")";
                ticketCountNum = progress;
                ticketCountText.setText(tCountStr);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        };

        ticketSeekBar.setOnSeekBarChangeListener(seekBarListener);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.r_btnMultiple) {
                    tCountLayout.setVisibility(View.VISIBLE);
                    ticketCountNum = ticketSeekBar.getProgress();
                    ticketCountText.setText("Ticket count (" + ticketSeekBar.getProgress() + ")");
                } else {
                    tCountLayout.setVisibility(View.INVISIBLE);
                    ticketCountNum = 1;
                }
            }
        });



        movieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Change the poster image according to the selected movie
                switch (position) {
                    case 0:
                        posterImg_iv.setImageResource(R.drawable.shrek);
                        break;
                    case 1:
                        posterImg_iv.setImageResource(R.drawable.blade_runner);
                        break;
                    case 2:
                        posterImg_iv.setImageResource(R.drawable.taxi_driver);
                        break;
                    case 3:
                        posterImg_iv.setImageResource(R.drawable.v_for_vendetta);
                        break;
                    default:
                        posterImg_iv.setImageResource(R.drawable.shrek);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


    }

    private void clearEverything(){
        radioGroup.clearCheck();
        payment_rg.check(R.id.r_btnPaypal);
        movieSpinner.setSelection(0);
        ticketSeekBar.setProgress(2);
        ticketCountNum = 0;
        nameTxt.setText("");
        tCountLayout.setVisibility(View.INVISIBLE);
    }

    private void makeAndShowDialogBox(String dialogMsg){
        AlertDialog.Builder md = new AlertDialog.Builder(this);

        md.setTitle("Ticket Details");
        md.setMessage(dialogMsg);
        md.setIcon(R.drawable.cinema);

        md.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToastMsg("Successful payment :)");
            }
        });


        md.create();
        md.show();
    }

    private void displayToastMsg(String toastButtonIsClicked){
        Toast.makeText(getBaseContext(),toastButtonIsClicked,Toast.LENGTH_SHORT).show();
    }
}