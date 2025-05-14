package com.example.week14_extra2;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final int STORAGE_PERMISSION_CODE = 1001;
    private static final String IMAGE_URL = "https://aybu.edu.tr/GetFile?id=528368f5-fdce-4119-9777-cd1da02b839d.jpg";
    private boolean saveToPhotos = true; // Default option

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(view -> askSaveLocation());
    }

    // Step 1: Ask Save Location
    private void askSaveLocation() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Choose Save Location")
                .setMessage("Where would you like to save the downloaded image?")
                .setPositiveButton("Photos (Gallery)", (dialog, which) -> {
                    saveToPhotos = true;
                    checkPermissionsAndDownload();
                })
                .setNegativeButton("Files/Folders", (dialog, which) -> {
                    saveToPhotos = false;
                    checkPermissionsAndDownload();
                })
                .show();
    }

    // Step 2: Check Permissions
    private void checkPermissionsAndDownload() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                downloadImage();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, STORAGE_PERMISSION_CODE);
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                downloadImage();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, STORAGE_PERMISSION_CODE);
            }
        }
    }

    // Step 3: Handle Permission Results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadImage();
            } else {
                Toast.makeText(this, "Permission Denied. Unable to download image.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Step 4: Download the Image
    private void downloadImage() {
        new Thread(() -> {
            try {
                URL url = new URL(IMAGE_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                runOnUiThread(() -> saveImage(bitmap));
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(this, "Failed to download image: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                Log.e("Download Error", e.getMessage());
            }
        }).start();
    }

    // Step 5: Save the Image
    private void saveImage(Bitmap bitmap) {
        try {
            if (saveToPhotos) {
                saveToGallery(bitmap);
            } else {
                saveToFiles(bitmap);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error saving image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Save to Gallery (Photos)
    private void saveToGallery(Bitmap bitmap) {
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "downloaded_image.jpg");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyDownloadedImages");

            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                try (FileOutputStream out = (FileOutputStream) getContentResolver().openOutputStream(uri)) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    Toast.makeText(this, "Image saved to Photos.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Failed to save image.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error saving image to Photos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Save to Files/Folders
    private void saveToFiles(Bitmap bitmap) {
        try {
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MyDownloadedImages");
            if (!directory.exists()) directory.mkdirs();

            File imageFile = new File(directory, "downloaded_image.jpg");
            try (FileOutputStream out = new FileOutputStream(imageFile)) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                Toast.makeText(this, "Image saved to Downloads.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error saving image to Files: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}