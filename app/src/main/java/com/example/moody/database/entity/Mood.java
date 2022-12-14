package com.example.moody.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.moody.database.Feeling;

import java.util.Date;

@Entity
public class Mood {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    public Date date;
    public Feeling feeling;
    public String event;
}
