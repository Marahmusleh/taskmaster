package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskForm extends AppCompatActivity {
    String img = "";


    TaskDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);


        EditText text = findViewById(R.id.titleInput);
        EditText descp = findViewById(R.id.bodyInput);
        EditText taskState = findViewById(R.id.stateInput);
        Button submit = findViewById(R.id.submitTask);


        RadioButton radioButton = findViewById(R.id.team1);
        RadioButton radioButton1 = findViewById(R.id.team2);
        RadioButton radioButton2 = findViewById(R.id.team3);
//
//        RadioGroup radioGroup=findViewById(R.id.radioGroupId);
//        RadioButton radioButtons = findViewById(radioGroup.getCheckedRadioButtonId());
//        String radioString=radioButton.getText().toString();

        Button attacedFile = findViewById(R.id.attacedFilId);
        attacedFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFileFromDevice();
            }
        });

        List<Team> teams = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("MyAmplifyApp", team.getName());

                        teams.add(team);

                    }
                    Log.i("MyAmplifyApp", "Teams added");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = text.getText().toString();
                String body = descp.getText().toString();
                String state = taskState.getText().toString();
////
//                    taskDatabase = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
//
//                    Task task = new Task(title,body,state);
//                    taskDatabase.taskDao().insertAll(task);


                String teamName = "";
                if (radioButton.isChecked()) {
                    teamName = radioButton.getText().toString();
                } else if (radioButton1.isChecked()) {
                    teamName = radioButton1.getText().toString();
                } else if (radioButton2.isChecked()) {
                    teamName = radioButton2.getText().toString();

                }
                Team checkecdTeam = null;
                for (Team teams : teams) {
                    if (teams.getName().equals(teamName)) {
                        checkecdTeam = teams;
                    }
                }
                Task task = Task.builder()
                        .title(title)
                        .teams(checkecdTeam)
                        .img(img)
                        .body(body).state(state).build();

                Amplify.API.mutate(
                        ModelMutation.create(task),
                        response2 -> Log.i("MyAmplifyApp", "Task saved with id: " + response2.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "failure", error)
                );
                Intent goToHomePage = new Intent(TaskForm.this, MainActivity.class);
                startActivity(goToHomePage);
            }
        });
    }

    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a File");
        startActivityForResult(chooseFile, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFileCopied");
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
            OutputStream outputStream = new FileOutputStream(uploadFile);
            img = data.getData().toString();
            byte[] buff = new byte[1024];
            int length;
            while ((length = exampleInputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            exampleInputStream.close();
            outputStream.close();
            Amplify.Storage.uploadFile(
                    "image",
                    uploadFile,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}