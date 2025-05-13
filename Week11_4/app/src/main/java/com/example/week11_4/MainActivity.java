package com.example.week11_4;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText search;
    private TextView name;
    private TextView surname;
    private TextView salary;
    private ImageView photo;
    private DBAdapter dbdbadapter;
    private Bitmap mBitmap;
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

        search = (EditText) findViewById(R.id.et_name);
        name = (TextView) findViewById(R.id.nameDB);
        surname = (TextView) findViewById(R.id.surnameDB);
        salary = (TextView) findViewById(R.id.salaryDB);
        photo = (ImageView) findViewById(R.id.photoDB);

        // Database will be created using the DBAdapter
        dbdbadapter = new DBAdapter(this);

        // Add a contact
        // A Bitmap must be converted to a byte array so that it can be stored in a blob
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
        // mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.google);

        ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
        new Thread(() -> {
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, mByteArrayOutputStream);
            byte[] image = mByteArrayOutputStream.toByteArray();

            runOnUiThread(() -> {
                dbdbadapter.open();
                long id = dbdbadapter.insertContact("Tim", "Cook", 2000000, image);
                dbdbadapter.close();
                displayToast("Image saved to DB!");
            });
        }).start();
        // long id = db.insertContact("Larry", "page", 3000000, image);
        // long id = db.insertContact("Steve", "Ballmer", 5000000, image);
        dbdbadapter.close();
    }

    private void displayToast(String imageCompressedSuccessfully) {
        Toast.makeText(getBaseContext(), imageCompressedSuccessfully, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {

        if (search.getText().toString().isEmpty())
            Toast.makeText(this, "Type Search name", Toast.LENGTH_LONG).show();
        else {

            // Get a contact
            dbdbadapter.open();
            Cursor c = dbdbadapter.getContact(search.getText().toString());
            if (c.moveToFirst())
                DisplayContact(c);
            else
                Toast.makeText(this, "No record found", Toast.LENGTH_LONG)
                        .show();
            dbdbadapter.close();

        }

    }

    public void DisplayContact(Cursor c) {
        name.setText(c.getString(0));
        surname.setText(c.getString(1));
        salary.setText(c.getString(2));

        byte[] image = c.getBlob(3);
        Bitmap mBitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        photo.setImageBitmap(mBitmap);
    }
}