package com.example.taskmaster;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String taskName = extras.getString("task");
            TextView text = findViewById(R.id.taskDetailTitle);
            text.setText(taskName);
        }
    }
}