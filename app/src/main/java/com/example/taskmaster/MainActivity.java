package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTask.class);
            startActivity(intent);
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AllTasks.class);
            startActivity(intent);
        });

        // lab 27
        Button btn5 = findViewById(R.id.button5);
            btn5.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,TaskDetail.class);
            String text = btn5.getText().toString();
            intent.putExtra("task",text);
            startActivity(intent);
        });

        findViewById(R.id.button6).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,TaskDetail.class);
            Button btn6 = findViewById(R.id.button6);
            String text = btn6.getText().toString();
            intent.putExtra("task",text);
            startActivity(intent);
        });
        findViewById(R.id.button7).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,TaskDetail.class);
            Button btn7 = findViewById(R.id.button7);
            String text = btn7.getText().toString();
            intent.putExtra("task",text);
            startActivity(intent);
        });

        findViewById(R.id.button8).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
        });


    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("username","X");

        TextView nameView = findViewById(R.id.ShowName);
        nameView.setText(userName +"'s" +" "+ "tasks");
    }
}