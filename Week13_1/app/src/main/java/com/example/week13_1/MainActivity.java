package com.example.week13_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Button mButton;
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

        mButton = findViewById(R.id.btn);
    }

    public void onClick(View v) {
        new BackgroundTask().execute("https://aybu.edu.tr/GetFile?id=528368f5-fdce-4119-9777-cd1da02b839d.jpg");

        // mButton.setVisibility(View.INVISIBLE);
        mButton.setEnabled(false);

    }

    /*
     * AsyncTask<Params, Progress, Result>
     * The three types used by an asynchronous task are the following:
     * 1. Params, the type of the parameters sent to the task upon execution.
     * 2. Progress, the type of the progress units published during the background computation.
     * 3. Result, the type of the result of the background computation.
     */

    private class BackgroundTask extends AsyncTask<String, Void, Bitmap> {
        // The input argument "String" behaves like a string []. It is called Varargs
        protected Bitmap doInBackground(String... url) {
            // Download an image
            Bitmap bitmap = DownloadImage(url[0]);
            try {
                Thread.sleep(3000);			// Creating a 3s delay. You don't need to use it
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return bitmap;
        }


        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            // do Something
        }

        protected void onPostExecute(Bitmap bitmap) {
            mImageView = findViewById(R.id.img);
            mImageView.setImageBitmap(bitmap);
        }
    }

    // Or method
    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            // Custom Method
            in = OpenHttpConnection(URL);

            // Creating a Bitmap from InputStream
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            Toast.makeText(this, e1.getLocalizedMessage(), Toast.LENGTH_LONG)
                    .show();
            e1.printStackTrace();
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            // Sets allowUserInteraction. Unused by Android.
            httpConn.setAllowUserInteraction(false);

            // Sets whether this connection follows redirects
            httpConn.setInstanceFollowRedirects(true);

            // Sets the request command which will be sent to the remote HTTP server.
            // This method can only be called before the connection is made.
            httpConn.setRequestMethod("GET");

            // Opens a connection to the resource.
            httpConn.connect();

            // Obtaining the response code returned by the remote HTTP server.
            // -1 corresponds to an invalid response code
            response = httpConn.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }

        return in;
    }

}