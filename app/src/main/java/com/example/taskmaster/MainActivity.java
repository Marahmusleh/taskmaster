package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button8).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        });

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Lab1", "Bitmap", "new"));
        tasks.add(new Task("Lab2", "Codefellowship", "complete"));
        tasks.add(new Task("Lab3", "Spring and REST", "in progress"));

        RecyclerView allTasks = findViewById(R.id.ListOfTasks);

        allTasks.setLayoutManager(new LinearLayoutManager(this));


        allTasks.setAdapter(new TaskAdapter(tasks, new TaskAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intentTaskDetails = new Intent(getApplicationContext(), TaskDetail.class);
                intentTaskDetails.putExtra("title", tasks.get(position).title);
                intentTaskDetails.putExtra("body", tasks.get(position).body);
                intentTaskDetails.putExtra("state", tasks.get(position).state);
                startActivity(intentTaskDetails);

            }
        }));
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