package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
            //send team name to main by sharedPreferences
            RadioGroup radioGroup=findViewById(R.id.groupRadioButtonFromSettingId);
            RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            String radioString=radioButton.getText().toString();

            sharedPEditor.putString("username",name);
            sharedPEditor.putString("teamName",radioString);
            sharedPEditor.apply();

            Intent goToHomePage = new Intent(Settings.this, MainActivity.class);
            startActivity(goToHomePage);

        });
    }
}