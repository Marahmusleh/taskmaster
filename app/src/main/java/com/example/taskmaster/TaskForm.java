package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class TaskForm extends AppCompatActivity {

    TaskDatabase taskDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

//save to db
            EditText taskTitle = findViewById(R.id.titleInput);
            EditText taskBody = findViewById(R.id.bodyInput);
            EditText taskState = findViewById(R.id.stateInput);
            Button button = findViewById(R.id.submitTask);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = taskTitle.getText().toString();
                    String body = taskBody.getText().toString();
                    String state = taskState.getText().toString();
//
                    taskDatabase = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();

                    Task task = new Task(title,body,state);
                    taskDatabase.taskDao().insertAll(task);


                    Intent goToHomePage = new Intent(TaskForm.this, MainActivity.class);
                    startActivity(goToHomePage);
                }
            });
    }

}