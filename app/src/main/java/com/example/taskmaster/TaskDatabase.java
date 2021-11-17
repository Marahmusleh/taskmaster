package com.example.taskmaster;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TaskOrg.class}, version = 1)
    public abstract class TaskDatabase extends RoomDatabase {
        public abstract DaoTask taskDao();
    }

