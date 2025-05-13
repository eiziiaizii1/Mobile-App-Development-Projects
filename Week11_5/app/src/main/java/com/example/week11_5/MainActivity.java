package com.example.week11_5;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private TableLayout mTable;
    private TableRow mTableRow;
    private TextView mTextView1, mTextView2, mTextView3;
    private int records;
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

        // If the database already exist no need to copy it from the assets
        // folder
        try {
            String destPath = "/data/data/" + getPackageName()
                    + "/databases/mydb2.db";
            File file = new File(destPath);
            File path = new File("/data/data/" + getPackageName()
                    + "/databases/");
            if (!file.exists()) {
                path.mkdirs();
                CopyDB(getBaseContext().getAssets().open("mydb2.db"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mTable = findViewById(R.id.mTable);
        DBAdapter dbadapter = new DBAdapter(this);

        // Opening the database for reading data
        dbadapter.open();
        // Getting all rows/record from the database
        Cursor c = dbadapter.getAllContacts();
        // Obtaining a count for the number of rows/records
        records = c.getCount();
        // Move the cursor to the first row/record
        c.moveToFirst();

        // Creating a layout that is to be applied to rows
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1.0f);

        // Creating the first row (it will contain headings)
        mTableRow = new TableRow(this);
        mTableRow.setLayoutParams(tableRowParams);
        mTable.addView(mTableRow);

        // Adding 3 TextViews to hold headings (for each column)
        // Displaying 1st column heading
        mTextView1 = new TextView(this);
        mTableRow.addView(mTextView1);
        mTextView1.setText("Department");
        mTextView1.setTextColor(Color.rgb(0, 200, 0));

        // Displaying 2nd column heading
        mTextView2 = new TextView(this);
        mTableRow.addView(mTextView2);
        mTextView2.setText("Instructors");
        mTextView2.setTextColor(Color.rgb(0, 200, 0));

        // Displaying 3rd column heading
        mTextView3 = new TextView(this);
        mTableRow.addView(mTextView3);
        mTextView3.setText("Students");
        mTextView3.setTextColor(Color.rgb(0, 200, 0));

        // Fill the table with data rows dynamically
        for (int i = 0; i < records; i++) {
            // Creating data rows
            mTableRow = new TableRow(this);
            mTableRow.setLayoutParams(tableRowParams);
            mTable.addView(mTableRow);

            // Table row contains 3 TextViews. Each to hold data for each column
            // Displaying data in 1st column
            mTextView1 = new TextView(this);
            mTableRow.addView(mTextView1);
            mTextView1.setText(c.getString(1));

            // Displaying data in 2nd column
            mTextView2 = new TextView(this);
            mTableRow.addView(mTextView2);
            mTextView2.setText(c.getString(2));

            // Displaying data in 3rd column
            mTextView3 = new TextView(this);
            mTableRow.addView(mTextView3);
            mTextView3.setText(c.getString(3));

            // Move the cursor to the next row/record
            c.moveToNext();
        }

        // Closing the database
        dbadapter.close();
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void DisplayContact(Cursor c) {
        Toast.makeText(
                        this,
                        "id: " + c.getString(0) + "\n" + "Dept: " + c.getString(1)
                                + "\n" + "Instructors: " + c.getString(2) + "\n"
                                + "Students:  " + c.getString(3), Toast.LENGTH_LONG)
                .show();
    }

    private void displayToast(String msg) {
        Toast.makeText(getBaseContext(), "Number of Records = " + msg,
                Toast.LENGTH_SHORT).show();
    }
}