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
            String taskName = extras.getString("title");
            TextView text = findViewById(R.id.taskDetailTitle);
            text.setText(taskName);

            String taskBody = extras.getString("body");
            TextView text2 = findViewById(R.id.taskDetaiDesc);
            text2.setText(taskBody);

            String taskState = extras.getString("state");
            TextView text3 = findViewById(R.id.test);
            text3.setText(taskState);
        }
    }
}