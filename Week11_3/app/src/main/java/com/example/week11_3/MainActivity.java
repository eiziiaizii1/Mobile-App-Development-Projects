package com.example.week11_3;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
    Cursor c=null;
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

        // If the database already exist no need to copy it from the assets folder
        try {
            String destPath = "/data/data/" + getPackageName() + "/databases/mydb";
            File file = new File(destPath);
            File path = new File("/data/data/" + getPackageName() + "/databases/");
            if (!file.exists()) {
                path.mkdirs();
                CopyDB( getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DBAdapter dbadapter = new DBAdapter(this);


        // Add a contact
        dbadapter.open();
        long id = dbadapter.insertContact("Fadi Yilmaz", "fadiyilmaz@aybu.edu.tr");
        id = dbadapter.insertContact("Fatih Nar", "fnar@aybu.edu.tr");
        id = dbadapter.insertContact("Yusuf Evren Aykaç", "yeaykac@aybu.edu.tr");
        dbadapter.close();


        // Get all contacts
        dbadapter.open();
        c = dbadapter.getAllContacts();
        if (c.moveToFirst())
        {
            do {
                DisplayContact(c);
            } while (c.moveToNext());
        }
        dbadapter.close();


        // Get a contact
        dbadapter.open();
        Cursor c = dbadapter.getContact(2);
        if (c.moveToFirst())
            DisplayContact(c);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        dbadapter.close();



        // Update contact
        dbadapter.open();
        if (dbadapter.updateContact(1, "Fatih Nar", "fatihnar@gmail.com"))
            Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
        dbadapter.close();



        // Delete a contact
        dbadapter.open();
        if (dbadapter.deleteContact(1))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        dbadapter.close();
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void DisplayContact(Cursor c)
    {
        Toast toast = new Toast(getApplicationContext());
        toast.setText("id: " + c.getString(0) + "\n" +
                "Name: " + c.getString(1) + "\n" +
                "Email:  " + c.getString(2));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}