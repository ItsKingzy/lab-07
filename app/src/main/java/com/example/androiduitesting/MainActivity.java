package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    EditText newName;
    LinearLayout nameField;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.field_nameEntry);
        newName  = findViewById(R.id.editText_name);

        cityList = findViewById(R.id.city_list);

        //String []cities ={"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();

        //dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);


        cityList.setAdapter(cityAdapter);

        final Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameField.setVisibility(View.VISIBLE);
            }
        });

        final Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cityName = newName.getText().toString();
                if (cityName.isEmpty()) {
                    // Added an error message in case the user inputs nothing
                    Toast.makeText(MainActivity.this,
                            "Please enter a city name", Toast.LENGTH_SHORT).show();
                    return;
                }
                cityAdapter.add(cityName);
                newName.getText().clear();
                nameField.setVisibility(View.INVISIBLE);
            }
        });

        final Button deleteButton = findViewById(R.id.button_clear);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cityAdapter.clear();
            }
        });

        // Replacement for Fragments view
        // Source: https://www.geeksforgeeks.org/android/what-is-intent-in-android/
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            // Get position of clicked city
            String selectedCity = dataList.get(position);

            // Set intent for the Show Activity page
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            intent.putExtra("aCity", selectedCity); // Put selected city into the intent
            startActivity(intent); // Start up the intent view
        });
    }
}