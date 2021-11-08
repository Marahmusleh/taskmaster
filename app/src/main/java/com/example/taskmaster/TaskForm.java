package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

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
////
//                    taskDatabase = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
//
//                    Task task = new Task(title,body,state);
//                    taskDatabase.taskDao().insertAll(task);

                    Task todo = Task.builder()
                            .title(title)
                            .body(body)
                            .state(state)
                            .build();

                    Amplify.API.mutate(
                            ModelMutation.create(todo),
                            response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                            error -> Log.e("MyAmplifyApp", "Create failed", error)
                    );

                    Intent goToHomePage = new Intent(TaskForm.this, MainActivity.class);
                    startActivity(goToHomePage);
                }
            });
    }

}