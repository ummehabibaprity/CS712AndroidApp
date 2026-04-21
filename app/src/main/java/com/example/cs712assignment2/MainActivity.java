package com.example.cs712assignment2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String MY_ACTION = "com.example.MY_ACTION";
    private static final int REQUEST_MSE712_PERMISSION = 200;
    private static final String CUSTOM_PERMISSION = "com.example.cs712assignment2.MSE712";

    private MyBroadcastReceiver receiver;
    private boolean receiverRegistered = false;

    Button btnExplicit, btnImplicit, btnImageActivity;

    private void checkAndRequestMse712Permission() {
        if (ContextCompat.checkSelfPermission(this, CUSTOM_PERMISSION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{CUSTOM_PERMISSION},
                    REQUEST_MSE712_PERMISSION
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_MSE712_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "MSE712 permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "MSE712 permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExplicit = findViewById(R.id.btnExplicit);
        btnImplicit = findViewById(R.id.btnImplicit);
        btnImageActivity = findViewById(R.id.btnImageActivity);

        Button btnStartService = findViewById(R.id.btnStartService);
        Button btnSendBroadcast = findViewById(R.id.btnSendBroadcast);

        receiver = new MyBroadcastReceiver();

        checkAndRequestMse712Permission();

        // Explicit Intent
        btnExplicit.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, CUSTOM_PERMISSION)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{CUSTOM_PERMISSION},
                        REQUEST_MSE712_PERMISSION
                );
                Toast.makeText(this, "Please grant MSE712 permission first", Toast.LENGTH_SHORT).show();
            }
        });

        // Implicit Intent
        btnImplicit.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.ACTION_VIEW_CHALLENGES");
            startActivity(intent);
        });

        // Open Third Activity
        btnImageActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        // Notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
        }

        // START SERVICE BUTTON
        btnStartService.setOnClickListener(v -> {
            Toast.makeText(this, "Start Service clicked", Toast.LENGTH_SHORT).show();

            Intent serviceIntent = new Intent(MainActivity.this, MyForegroundService.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent);
            } else {
                startService(serviceIntent);
            }
        });

        // SEND BROADCAST BUTTON
        btnSendBroadcast.setOnClickListener(v -> {
            Toast.makeText(this, "Broadcast Sent", Toast.LENGTH_SHORT).show();

            Intent broadcastIntent = new Intent(MY_ACTION);
            sendBroadcast(broadcastIntent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!receiverRegistered) {
            IntentFilter filter = new IntentFilter(MY_ACTION);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED);
            } else {
                registerReceiver(receiver, filter);
            }

            receiverRegistered = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (receiverRegistered) {
            unregisterReceiver(receiver);
            receiverRegistered = false;
        }
    }
}