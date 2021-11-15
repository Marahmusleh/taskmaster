package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TaskDatabase taskDatabase;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAmplify();

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

       //Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );
//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "altarifimara7@gmail.com")
//                .build();
//        Amplify.Auth.signUp("marah", "Password123", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
//
//        Amplify.Auth.confirmSignUp(
//                "marah",
//                "387813",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );

//        Button signOut=findViewById(R.id.signOut);
//        signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Amplify.Auth.signOut(
//                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );
//            }
//        });
//    }
        Button signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView allTasks = findViewById(R.id.ListOfTasks);

        allTasks.setLayoutManager(new LinearLayoutManager(this));

        List<Task> taskOrgs = new ArrayList<>();
        ArrayList<Task> teams = new ArrayList<>();


        allTasks.setAdapter(new TaskAdapter(teams, new TaskAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intentTaskDetails = new Intent(getApplicationContext(), TaskDetail.class);
                intentTaskDetails.putExtra("title", teams.get(position).getTitle());
                intentTaskDetails.putExtra("body", teams.get(position).getBody());
                intentTaskDetails.putExtra("state", teams.get(position).getState());
                intentTaskDetails.putExtra("img",teams.get(position).getImg());
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

        SharedPreferences sharedPreferencest = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String teamN = sharedPreferencest.getString("teamName", "team name");
        TextView textView = findViewById(R.id.viewteamNameId);
        textView.setText(teamN);

        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        taskOrgs.add(task);
                        for (int i = 0; i < taskOrgs.size(); i++) {
                            if (taskOrgs.get(i).getTeams().getName().equals(teamN)) {
                                teams.add(taskOrgs.get(i));
                            }
                        }
                    }
                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "done");
                },
                error -> Log.e("MyAmplifyApp", "failure", error)
        );

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userNameAPI", "X");

//        CharSequence myUserTitle = sharedPreferences.getString("userNameAPI", "");
//        setTitle(myUserTitle + " Profile");

        TextView nameView = findViewById(R.id.ShowName);
        nameView.setText(userName + "'s" + " " + "tasks");
    }
        private void configureAmplify () {
            try {
//                Log.i("MyAmplifyApp", "Initialized Amplify");
//            Team team = Team.builder()
//                    .name("First team")
//                    .build();
//
//            Amplify.API.mutate(
//                    ModelMutation.create(team),
//                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//            );
//
//            ///second team
//
//            Team teamTow = Team.builder()
//                    .name("Second team")
//                    .build();
//
//            Amplify.API.mutate(
//                    ModelMutation.create(teamTow),
//                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//            );
//
//            ////third team hard coby
//
//            Team teamThree = Team.builder()
//                    .name("Third team")
//                    .build();
//
//            Amplify.API.mutate(
//                    ModelMutation.create(teamThree),
//                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//
//            );
                Amplify.addPlugin(new AWSDataStorePlugin());
                Amplify.addPlugin(new AWSApiPlugin());
                Amplify.configure(getApplicationContext());
                Log.i(TAG, "Successfully initialized Amplify plugins");
            } catch (AmplifyException exception) {
                Log.e(TAG, "Failed to initialize Amplify plugins: " + exception.toString());
            }
        }
    }






