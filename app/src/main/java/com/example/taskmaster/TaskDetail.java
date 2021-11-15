package com.example.taskmaster;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;

import java.io.File;

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

            String img= extras.getString("img");
            Amplify.Storage.downloadFile(
                    "image",
                    new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                    result -> {
                        ImageView image = findViewById(R.id.imgeViewIdDetail);
                        extras.getString("img");
                        image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));

                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile());},
                    error -> Log.e("MyAmplifyApp",  "Download Failure", error)
            );
        }
    }
}