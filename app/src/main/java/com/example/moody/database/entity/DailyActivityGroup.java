package com.example.moody.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DailyActivityGroup {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public DailyActivityGroup(String name) {
        this.name = name;
    }
}
