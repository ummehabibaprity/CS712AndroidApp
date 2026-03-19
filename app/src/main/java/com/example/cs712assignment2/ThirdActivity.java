package com.example.cs712assignment2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ThirdActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnCapture;

    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Bundle extras = result.getData().getExtras();
                            if (extras != null) {
                                Object imageObject = extras.get("data");
                                if (imageObject instanceof Bitmap) {
                                    Bitmap photo = (Bitmap) imageObject;
                                    imageView.setImageBitmap(photo);
                                } else {
                                    Toast.makeText(this, "No image returned", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "Image capture failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Camera cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });

    private final ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    isGranted -> {
                        if (isGranted) {
                            openCamera();
                        } else {
                            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnCapture = findViewById(R.id.btnCapture);
        imageView = findViewById(R.id.imageView);

        btnCapture.setOnClickListener(v -> checkCameraPermissionAndOpen());
    }

    private void checkCameraPermissionAndOpen() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            cameraLauncher.launch(cameraIntent);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }
}