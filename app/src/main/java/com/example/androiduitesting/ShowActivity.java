package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView cityName = findViewById(R.id.cityName);
        Button backButton = findViewById(R.id.backButton);

        // Get the city name from the intent using the key "aCity"
        String city = getIntent().getStringExtra("aCity");

        // Set the text in the TextView to the city name
        cityName.setText(city);

        // Back button closes this screen
        backButton.setOnClickListener(view -> {
            finish();
        });
    }
}
