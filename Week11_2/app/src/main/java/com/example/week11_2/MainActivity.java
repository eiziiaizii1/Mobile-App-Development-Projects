package com.example.week11_2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private static final int READ_BLOCK_SIZE = 100;
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

        // Reading the file and displaying it in Toast
        InputStream is = this.getResources().openRawResource(R.raw.textfile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = null;
        try {
            while ((str = br.readLine()) != null)
                displayToast(str);

            is.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        editText = (EditText) findViewById(R.id.et1);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            String str = editText.getText().toString();
            try {
                // Internal Storage
                FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);

        /*
        // SD Card Storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/MyFiles");
        directory.mkdirs();
        File file = new File(directory,"textfile.txt");
        FileOutputStream fOut = new FileOutputStream(file);
        */

                OutputStreamWriter osw = new OutputStreamWriter(fOut);

                // Write the string to the file
                osw.write(str);

                // Make sure that any remaining data is written
                osw.flush();
                osw.close();

                // Display file saved message
                displayToast("File saved successfully!");

                // Clears the EditText
                editText.setText("");

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        } else if (view.getId() == R.id.btnLoad) {
            try {
                // Internal Storage
                FileInputStream fIn = openFileInput("textfile.txt");
                InputStreamReader isr = new InputStreamReader(fIn);

        /*
        // SD Storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/MyFiles");
        File file = new File(directory, "textfile.txt");
        FileInputStream fIn = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fIn);
        */

                char[] inputBuffer = new char[READ_BLOCK_SIZE];
                String s = "";

                int charRead;
                while ((charRead = isr.read(inputBuffer)) > 0) {
                    // Convert the chars to a String
                    String readString = String.copyValueOf(inputBuffer, 0, charRead);
                    s += readString;

                    inputBuffer = new char[READ_BLOCK_SIZE];
                }

                // Set the EditText to the text that has been read
                editText.setText(s);
                displayToast("File loaded successfully!");

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void displayToast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}