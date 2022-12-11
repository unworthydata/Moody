package com.example.moody.database.entity;

import androidx.room.PrimaryKey;

public class DailyActivityGroup {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
}
