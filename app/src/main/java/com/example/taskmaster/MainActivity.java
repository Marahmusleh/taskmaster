package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    String teamName = "";
//    StringBuilder teamId = new StringBuilder();

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
            Intent intent = new Intent(MainActivity.this, TaskForm.class);
            startActivity(intent);
        });

//       taskDatabase = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
//        List<Task> tasks = taskDatabase.taskDao().getAll();

        try {
            /////////to add three hard coding to team /////////////////

            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin()); // stores things in DynamoDB and allows us to perform GraphQL queries
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
            Team team = Team.builder()
                    .name("First team")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(team),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );

            ///second team

            Team teamTow = Team.builder()
                    .name("Tow team")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(teamTow),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );

            ////third team hard coby

            Team teamThree = Team.builder()
                    .name("Three team")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(teamThree),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }
        @Override
        protected void onStart() {
            super.onStart();

        RecyclerView allTasks = findViewById(R.id.ListOfTasks);

        allTasks.setLayoutManager(new LinearLayoutManager(this));

        List<Task> taskOrgs = new ArrayList<>();
        ArrayList<Task>teams=new ArrayList<>();


        allTasks.setAdapter(new TaskAdapter(teams, new TaskAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intentTaskDetails = new Intent(getApplicationContext(), TaskDetail.class);
                intentTaskDetails.putExtra("title", teams.get(position).getTitle());
                intentTaskDetails.putExtra("body", teams.get(position).getBody());
                intentTaskDetails.putExtra("state", teams.get(position).getState());
                startActivity(intentTaskDetails);
            }
        }));

        // ***********************lab 32 with lab33

        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTasks.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        SharedPreferences sharedPreferencest= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String teamNameString=sharedPreferencest.getString("teamName","team name");
        TextView teamNameView=findViewById(R.id.viewteamNameId);
        teamNameView.setText(teamNameString);

        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    ///looping through data to render it
                    for (Task taskMaster : response.getData()) {
                        ///add new data to array
                        taskOrgs.add(taskMaster);
                        for (int i = 0; i <taskOrgs.size() ; i++) {
                            if(taskOrgs.get(i).getTeams().getName().equals(teamNameString)){
                                teams.add(taskOrgs.get(i));
                            }
                        }
                    }
                    //handel promise and wait to get all data
                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "outsoid the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


//        Handler handler2 = new Handler(Looper.myLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                Log.i("query", "handleMessage: inside handler2");
//                Amplify.API.query(
//                        ModelQuery.list(Team.class),
//                        response -> {
//                            for (Team team : response.getData()) {
//                                if (team.getName().equals("team marah")) {
//                                    teamId.append(team.getId());
//                                }
//                            }
//                            Amplify.API.query(
//                                    ModelQuery.list(Task.class),
//                                    response2 -> {
//                                        for (Task todo : response2.getData()) {
//                                            if (todo.getTeamId().equals(teamId.toString()))
//                                                taskOrgs.add(todo);
//                                        }
//                                        handler.sendEmptyMessage(1);
//                                    },
//                                    error -> Log.e("MyAmplifyApp", "Query failure", error)
//                            );
//                        },
//                        error -> Log.e("MyAmplifyApp", "Query failure", error)
//                );
//
//                return false;
//            }
//        });
//        Team team1 = Team.builder().name("team marah").build();
//        Amplify.API.mutate(
//                ModelMutation.create(team1),
//                response2 -> {
//                    Log.i("MyAmplifyApp", "Added Team1: " + response2.getData().getId());
//                    handler2.sendEmptyMessage(2);
////                    Log.i("query", "handleMessage: calling handler 2"+response2.getData().getId());
//
//                },
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
//}
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