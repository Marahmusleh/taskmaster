package com.example.taskmaster;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.saveBtn).setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor sharedPEditor = sharedPreferences.edit();

            EditText username = findViewById(R.id.username);
            String name = username.getText().toString();

            sharedPEditor.putString("username",name);
            sharedPEditor.apply();
        });
    }
}