package com.example.taskmaster;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoTask {
    @Query("SELECT * FROM TaskOrg")
    List<TaskOrg> getAll();

    @Insert
    void insertAll(TaskOrg... taskOrgs);

    @Delete
    void delete(TaskOrg taskOrg);
}
