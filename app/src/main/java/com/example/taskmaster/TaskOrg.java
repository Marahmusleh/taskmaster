package com.example.taskmaster;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskOrg {
    @PrimaryKey(autoGenerate = true)
    public int id;

@ColumnInfo(name = "title")
    public String title;

@ColumnInfo(name = "body")
    public String body;

@ColumnInfo(name = "state")
    public String state;

    public TaskOrg(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}
