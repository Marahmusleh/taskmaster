package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TaskDatabase taskDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button8).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        });

        findViewById(R.id.addTaskBtn).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,TaskForm.class);
            startActivity(intent);
        });

       taskDatabase = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<Task> tasks = taskDatabase.taskDao().getAll();

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