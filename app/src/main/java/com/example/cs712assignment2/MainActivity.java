package com.example.cs712assignment2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String MY_ACTION = "com.example.MY_ACTION";
    private MyBroadcastReceiver receiver;
    private boolean receiverRegistered = false;

    Button btnExplicit, btnImplicit, btnImageActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExplicit = findViewById(R.id.btnExplicit);
        btnImplicit = findViewById(R.id.btnImplicit);
        btnImageActivity = findViewById(R.id.btnImageActivity);

        // Explicit Intent
        btnExplicit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
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
        Button btnStartService = findViewById(R.id.btnStartService);
        Button btnSendBroadcast = findViewById(R.id.btnSendBroadcast);

        receiver = new MyBroadcastReceiver();


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
