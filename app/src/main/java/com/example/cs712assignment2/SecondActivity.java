package com.example.cs712assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView listView = findViewById(R.id.challengeList);
        Button btnBack = findViewById(R.id.btnBack);

        String[] challenges = {"\n\n\nMobile Software Engineering Challenges:\n\n" +
                "1. Device Fragmentation",
                "2. Battery Optimization",
                "3. App Lifecycle Complexity",
                "4. Security and Privacy",
                "5. Rapid SDK Changes"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challenges);
        listView.setAdapter(adapter);

        btnBack.setOnClickListener(v ->
                startActivity(new Intent(SecondActivity.this, MainActivity.class)));
    }
}
