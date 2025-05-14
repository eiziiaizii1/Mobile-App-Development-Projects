package com.example.week13_3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.AsyncTask;

public class MyService extends Service {

    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return START_STICKY.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        // Calling DownloadFile() directly here will cause the UI to block.
        // Use this recommended method
        try {
            new DoBackgroundTask().execute(
                    new URL("http://www.amazon.com/somefiles.pdf"),
                    new URL("http://www.wrox.com/somefiles.pdf"),
                    new URL("http://www.google.com/somefiles.pdf"),
                    new URL("http://www.learn2develop.net/somefiles.pdf"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    private int DownloadFile(URL url) {
        try {
            // Simulate taking some time to download a file
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Return an arbitrary number representing the size of the downloaded file(s)
        return 100;
    }

    // AsyncTask enables proper and easy use of the UI thread. This class allows
    // to perform background operations and publish results on the UI thread
    // without having to manipulate threads and/or handlers.
    private class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {
        // This method takes an array of the first generic type specified earlier. In this
        // case, the type is URL. This method is executed in the background thread and is
        // where one should place the long-running code.
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            for (int i = 0; i < count; i++) {
                totalBytesDownloaded += DownloadFile(urls[i]);
                // Calculate percentage of download files and report its progress
                publishProgress((int) (((i+1) / (float) count) * 100));
            }
            return totalBytesDownloaded;
        }

        // This method is invoked in the UI thread and is called when you call the publishProgress()
        // method. It takes an array of the second generic type specified through AsyncTask.
        protected void onProgressUpdate(Integer... progress) {
            Log.d("Downloading files", String.valueOf(progress[0]) + "% downloaded");
            Toast.makeText(getBaseContext(), String.valueOf(progress[0]) + "% downloaded",
                    Toast.LENGTH_LONG).show();
        }

        // This method is invoked in the UI thread and is called when the doInBackground()
        // method has finished execution.
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(), "Downloaded " + result + " bytes",
                    Toast.LENGTH_LONG).show();

            // when the background thread has finished execution, one needs to manually call
            // the stopSelf() method to stop the service. Otherwise the service will keep on
            // running and can take system resources.
            stopSelf();
        }
    }
}